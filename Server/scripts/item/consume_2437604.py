if sm.canHold(2000005):
    sm.giveItem(2000005, 100)
    sm.consumeItem(parentID)
else:
    sm.sendSayOkay("Please make more room")