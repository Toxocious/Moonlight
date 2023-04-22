# Balrog Soul
souls = [2591124, 2591125, 2591126, 2591127, 2591128, 2591129, 2591130, 2591130, 2591085]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431660, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431660, 10)
    sm.giveItem(soul)