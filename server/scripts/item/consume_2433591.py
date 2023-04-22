# Crimson Queen Soul
souls = [2591444, 2591445, 2591446, 2591447, 2591448, 2591449, 2591450, 2591451, 2591409]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2433591, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2433591, 10)
    sm.giveItem(soul)