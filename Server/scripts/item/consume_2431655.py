# Spirit of Rock Soul
souls = [2591089, 2591090, 2591091, 2591092, 2591093, 2591094, 2591095]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431655, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431655, 10)
    sm.giveItem(soul)