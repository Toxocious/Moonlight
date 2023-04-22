# Prison Guard Ani Soul
souls = [2591096, 2591097, 2591098, 2591099, 2591100, 2591101, 2591102]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431656, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431656, 10)
    sm.giveItem(soul)