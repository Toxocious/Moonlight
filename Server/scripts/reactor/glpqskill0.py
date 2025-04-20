
# Warrior Sigil - 6109000

WarriorJobs = [100,110,111,112,120,121,122,130,131,132,1100,1110,1111,1112,2100,2110,2111,2112,3100,3110,3111,3112,3101,3120,3121,3122,3700,3710,3711,3712,4100,4110,4111,4112,5100,5110,5111,5112,6100,6110,6111,6112,10000,10100,10110,10111,10112]
FieldProperties = ["WarriorSigil","ArcherSigil","MageSigil","ThiefSigil","PirateSigil"]
SigilIDS = [6109000,6109001,6109002,6109003,6109004]

reactor.incHitCount()

if sm.getFieldID() == 610030200:
    if chr.getJob() in WarriorJobs:
        if reactor.getHitCount() == 1:
            sm.changeReactorState(6109000,1,1,1)
            sm.chatBlue("The Warrior Sigil has been activated!")
            sm.setFieldProperty("WarriorSigil",True)
            if sm.fieldHasProperty("ArcherSigil") and sm.fieldHasProperty("MageSigil") and sm.fieldHasProperty("ThiefSigil") and sm.fieldHasProperty("PirateSigil"):
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
#if sm.getFieldID() == 610030300:
