
# Archer Sigil - 6109001

ArcherJobs = [300,310,311,312,320,321,322,1300,1310,1311,1312,2002,2300,2310,2311,2312,3300,3310,3311,3312]
FieldProperties = ["WarriorSigil","ArcherSigil","MageSigil","ThiefSigil","PirateSigil"]
SigilIDS = [6109000,6109001,6109002,6109003,6109004]

reactor.incHitCount()

if sm.getFieldID() == 610030200:
    if chr.getJob() in ArcherJobs:
        if reactor.getHitCount() == 5:
            sm.changeReactorState(6109001,1,1,1)
            sm.chatBlue("The Archer Sigil has been activated!")
            sm.setFieldProperty("ArcherSigil",True)
            if sm.fieldHasProperty("WarriorSigil") and sm.fieldHasProperty("MageSigil") and sm.fieldHasProperty("ThiefSigil") and sm.fieldHasProperty("PirateSigil"):
                sm.chatBlue("The Antellion grants you access to the next portal! proceed.")
                sm.setFieldProperty("Stage1Cleared", True)
                sm.dispose()
    else:
        for x in range(len(FieldProperties)):
            sm.setFieldProperty(str(FieldProperties[x]), False)
        sm.chatRed("Someone has tampered with the wrong Sigil!")
        sm.chatRed("All of the Sigils have been reset!")
        for x in range(len(SigilIDS)):
            sm.changeReactorState(int(SigilIDS[x]),0,0,0)