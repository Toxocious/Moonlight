# Xerxes Soul
souls = [2591164, 2591165, 2591166, 2591167, 2591168, 2591169, 2591170]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431709, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431709, 10)
    sm.giveItem(soul)