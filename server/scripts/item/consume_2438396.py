# Will Soul
souls = [2591641, 2591642, 2591643, 2591644, 2591645, 2591646, 2591647, 2591648, 2591640]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2438396):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2438396, 10)
    sm.giveItem(soul)