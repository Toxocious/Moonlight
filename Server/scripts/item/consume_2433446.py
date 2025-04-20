# Pierre Soul
souls = [2591428, 2591429, 2591430, 2591431, 2591432, 2591433, 2591434, 2591435, 2591391]
rand = sm.getRandomIntBelow(len(souls))
if not sm.hasItem(2433446, 10):
    sm.sendSayOkay("You need at least 10 soul fragments to create a soul.")
else:
    soul = souls[rand]
    sm.consumeItem(2433446, 10)
    sm.giveItem(soul)