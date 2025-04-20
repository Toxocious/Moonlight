# Black Knight Soul
souls = [2591342, 2591343, 2591344, 2591345, 2591346, 2591347, 2591348, 2591349, 2591305]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2432575, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2432575, 10)
    sm.giveItem(soul)