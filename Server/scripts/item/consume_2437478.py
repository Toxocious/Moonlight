# Papulatus Soul
souls = [2591620, 2591621, 2591622, 2591623, 2591624, 2591625, 2591626, 2591627, 2591619]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2437478, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2437478, 10)
    sm.giveItem(soul)