if sm.canHold(5211068):
    sm.giveItem(5211068, 1, 1) # 1 Hour
    sm.consumeItem(2430404)
else:
    sm.setSpeakerID(1540809)
    sm.sendSayOkay("Please make room in your inventory.")