# Zakum Soul
souls = [2591171, 2591172, 2591173, 2591174, 2591175, 2591176, 2591177, 2591178, 2591163]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431710, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431710, 10)
    sm.giveItem(soul)