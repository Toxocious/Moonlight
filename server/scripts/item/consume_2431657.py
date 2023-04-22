# Dragon Rider Soul
souls = [2591103, 2591104, 2591105, 2591106, 2591107, 2591108, 2591109]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431657, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431657, 10)
    sm.giveItem(soul)