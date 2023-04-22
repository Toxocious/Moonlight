# Lotus Soul
souls = [2591460, 2591461, 2591462, 2591463, 2591464, 2591465, 2591466, 2591467, 2591427]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2433593, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2433593, 10)
    sm.giveItem(soul)