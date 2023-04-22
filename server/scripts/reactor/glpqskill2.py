
# Mage Sigil - 6109002

MageJobs = [200,210,211,212,220,221,222,230,231,232,1200,1210,1211,1212,2210,2212,2214,2218,2004,2700,2710,2711,2712,3200,3210,3211,3212,4200,4210,4211,4212,11200,11210,11211,11212,14000,14200,14210,14211,14212]
FieldProperties = ["WarriorSigil","ArcherSigil","MageSigil","ThiefSigil","PirateSigil"]
SigilIDS = [6109000,6109001,6109002,6109003,6109004]

reactor.incHitCount()

if sm.getFieldID() == 610030200:
    if chr.getJob() in MageJobs:
        if reactor.getHitCount() == 5:
            sm.changeReactorState(6109002,1,1,1)
            sm.chatBlue("The Mage Sigil has been activated!")
            sm.setFieldProperty("MageSigil",True)
            if sm.fieldHasProperty("WarriorSigil") and sm.fieldHasProperty("ArcherSigil") and sm.fieldHasProperty("ThiefSigil") and sm.fieldHasProperty("PirateSigil"):
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