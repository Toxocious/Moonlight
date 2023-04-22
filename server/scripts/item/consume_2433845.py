# Red Tiger Soul
souls = [2591476, 2591477, 2591478, 2591479, 2591480, 2591481, 2591482, 2591483, 2591485]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2433845, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2433845, 10)
    sm.giveItem(soul)