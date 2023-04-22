# Von Bon Soul
souls = [2591436, 2591437, 2591438, 2591439, 2591440, 2591441, 2591442, 2591443, 2591400]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2433515, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2433515, 10)
    sm.giveItem(soul)