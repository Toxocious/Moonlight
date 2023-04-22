# Elunite Elemental Soul
souls = [2591602, 2591603, 2591604, 2591605, 2591606, 2591607, 2591608, 2591609, 2591610]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2436980, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2436980, 10)
    sm.giveItem(soul)