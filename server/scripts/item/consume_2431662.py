# Von Leon Soul
souls = [2591132, 2591133, 2591134, 2591135, 2591136, 2591137, 2591138, 2591139, 2591086]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431662, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431662, 10)
    sm.giveItem(soul)