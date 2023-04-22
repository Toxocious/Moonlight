# Mu Gong Soul
souls = [2591117, 2591118, 2591119, 2591120, 2591121, 2591122, 2591123]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431659, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431659, 10)
    sm.giveItem(soul)