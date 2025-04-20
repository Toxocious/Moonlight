
# Thief Sigil - 6109000

ThiefJobs = [400,410,411,412,420,421,422,430,431,432,433,434,1400,1400,1410,1411,1412,2003,2400,2410,2411,2412,3600,3610,3611,3612]
FieldProperties = ["WarriorSigil","ArcherSigil","MageSigil","ThiefSigil","PirateSigil"]
SigilIDS = [6109000,6109001,6109002,6109003,6109004]

reactor.incHitCount()

if sm.getFieldID() == 610030200:
    if chr.getJob() in ThiefJobs:
        if reactor.getHitCount() == 5:
            sm.changeReactorState(6109003,1,1,1)
            sm.chatBlue("The Thief Sigil has been activated!")
            sm.setFieldProperty("ThiefSigil",True)
            if sm.fieldHasProperty("WarriorSigil") and sm.fieldHasProperty("ArcherSigil") and sm.fieldHasProperty("MageSigil") and sm.fieldHasProperty("PirateSigil"):
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