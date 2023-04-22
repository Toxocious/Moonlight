# Vellum Soul
souls = [2591452, 2591453, 2591454, 2591455, 2591456, 2591457, 2591458, 2591459, 2591418]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2433592, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2433592, 10)
    sm.giveItem(soul)