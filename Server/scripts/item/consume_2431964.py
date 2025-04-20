# Magnus Soul
souls = [2591272, 2591273, 2591274, 2591275, 2591276, 2591277, 2591278, 2591279, 2591264]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431964, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431964, 10)
    sm.giveItem(soul)