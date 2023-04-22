# pinkbean Soul
souls = [2591087, 2591140, 2591141, 2591142, 2591143, 2591144, 2591145, 2591146, 2591147]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2431661, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2431661, 10)
    sm.giveItem(soul)