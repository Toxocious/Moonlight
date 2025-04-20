# Rampant Cyborg Soul
souls = [2591358, 2591359, 2591360, 2591361, 2591362, 2591363, 2591364, 2591365, 2591323]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2432577, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2432577, 10)
    sm.giveItem(soul)