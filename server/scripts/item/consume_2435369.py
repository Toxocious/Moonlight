# Damien Soul
souls = [2591573, 2591574, 2591575, 2591576, 2591577, 2591578, 2591579, 2591580, 2591572]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2435369, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2435369, 10)
    sm.giveItem(soul)