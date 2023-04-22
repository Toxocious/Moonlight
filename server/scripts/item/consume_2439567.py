# Verus Hilla Soul
souls = [2591660, 2591661, 2591662, 2591663, 2591664, 2591665, 2591666, 2591667, 2591659]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2439567):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2439567, 10)
    sm.giveItem(soul)