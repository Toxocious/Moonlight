# Pink Mong Soul
souls = [2591528, 2591529, 2591530, 2591531, 2591532, 2591533, 2591534]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2435031, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2435031, 10)
    sm.giveItem(soul)