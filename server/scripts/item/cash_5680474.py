if sm.canHold(2048310):
    sm.giveItem(2048310)
    sm.consumeItem(parentID)
else:
    sm.sendSayOkay("Please make more room")