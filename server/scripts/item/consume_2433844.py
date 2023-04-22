# Gold Dragon Soul
souls = [2591468, 2591469, 2591470, 2591471, 2591472, 2591473, 2591474, 2591475, 2591484]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2433844, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2433844, 10)
    sm.giveItem(soul)