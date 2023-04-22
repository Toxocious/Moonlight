# Black Slime Soul
souls = [2591265, 2591266, 2591267, 2591268, 2591269, 2591270, 2591271]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431963, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431963, 10)
    sm.giveItem(soul)