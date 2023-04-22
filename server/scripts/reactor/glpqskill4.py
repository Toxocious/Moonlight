
# Pirate Sigil - 6109000

PirateJobs = [500,501,508,510,511,512,520,521,522,530,531,532,570,571,572,1500,1510,1511,1512,2005,2500,2510,2511,2512,3500,3510,3511,3512,3600,3610,3611,3612,6001,6500,6510,6511,6512]
FieldProperties = ["WarriorSigil","ArcherSigil","MageSigil","ThiefSigil","PirateSigil"]
SigilIDS = [6109000,6109001,6109002,6109003,6109004]

reactor.incHitCount()

if sm.getFieldID() == 610030200:
    if chr.getJob() in PirateJobs:
        if reactor.getHitCount() == 5:
            sm.changeReactorState(6109004,1,1,1)
            sm.chatBlue("The Pirate Sigil has been activated!")
            sm.setFieldProperty("PirateSigil",True)
            if sm.fieldHasProperty("WarriorSigil") and sm.fieldHasProperty("ArcherSigil") and sm.fieldHasProperty("MageSigil") and sm.fieldHasProperty("ThiefSigil"):
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