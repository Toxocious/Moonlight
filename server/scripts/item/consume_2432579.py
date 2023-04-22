# Bad Brawler Soul
souls = [2591374, 2591375, 2591376, 2591377, 2591378, 2591379, 2591380, 2591381, 2591341]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2432579, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2432579, 10)
    sm.giveItem(soul)