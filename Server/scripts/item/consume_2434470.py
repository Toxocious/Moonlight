# Ursus Soul
souls = [2591509, 2591510, 2591511, 2591512, 2591513, 2591514, 2591515, 2591516, 2591517]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2434470, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2434470, 10)
    sm.giveItem(soul)