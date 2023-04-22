# Vicious Hunter Soul
souls = [2591366, 2591367, 2591368, 2591369, 2591370, 2591371, 2591372, 2591372, 2591332]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2432578, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2432578, 10)
    sm.giveItem(soul)