# Arkarium Soul
souls = [2591210, 2591211, 2591212, 2591213, 2591214, 2591215, 2591216, 2591217, 2591202]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431753, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431753, 10)
    sm.giveItem(soul)