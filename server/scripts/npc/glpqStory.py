
FieldProperties = ["WarriorSigil","ArcherSigil","MageSigil","ThiefSigil","PirateSigil"]
if sm.getFieldID() == 610030100:
    sm.sendNext("Agh, you have made it in. Let me tell you real quick: they've caught us already. Master Guardians are about to come here in about a minute. We'd better hurry.")
    sm.sendNext("The portal to the Twisted Masters is busted. We have to find an alternate way, one that will take us through many death traps.")
    sm.sendNext("You can find the portal somewhere around here... you'd better find it, quick. I'll catch up.")
if sm.getFieldID() == 610030200:

    if sm.fieldHasProperty("Stage1Cleared"):
        sm.sendSayOkay("Proceed to the next stage.")

    for x in range(len(FieldProperties)):
        if not sm.fieldHasProperty(str(FieldProperties[x])):
            sm.chatRed("Some of the Sigils have not been activated yet")

    else:
        sm.setFieldProperty("Stage1Cleared",True)
        sm.chatBlue("The Antellion grants you access to the next portal! Proceed!")
        sm.spawnNpc()