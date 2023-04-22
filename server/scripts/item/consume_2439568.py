# Darknell Soul
souls = [2591677, 2591678, 2591679, 2591680, 2591681, 2591682, 2591683, 2591684, 2591676]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2439568):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2439568, 10)
    sm.giveItem(soul)