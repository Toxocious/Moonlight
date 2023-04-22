# Pianus Soul
souls = [2591234, 2591235, 2591236, 2591237, 2591238, 2591239, 2591240]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431895, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431895, 10)
    sm.giveItem(soul)