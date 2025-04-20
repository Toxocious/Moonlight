if sm.canHold(5000605) and sm.canHold(2120000):
    sm.giveItem(5000605)
    sm.giveItem(2120000, 1000)
    sm.consumeItem(5680260)
else:
    sm.setSpeakerID(1540809)
    sm.sendSayOkay("Please make room in your inventory.")