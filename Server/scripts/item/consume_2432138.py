# Murgoth Soul
souls = [2591288, 2591289, 2591290, 2591291, 2591292, 2591293, 2591294, 2591295, 2591296]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2432138, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2432138, 10)
    sm.giveItem(soul)