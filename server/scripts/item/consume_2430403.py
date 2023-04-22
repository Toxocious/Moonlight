if sm.canHold(5211067):
    sm.giveItem(5211067, 1, 1) # 1 Hour
    sm.consumeItem(2430403)
else:
    sm.setSpeakerID(1540809)
    sm.sendSayOkay("Please make room in your inventory.")