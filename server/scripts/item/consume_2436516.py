# Epic Potential Scroll 50% Override Pack
if sm.canHold(2049705, 10):
    sm.consumeItem(parentID)
    sm.giveItem(2049705, 10)
else:
    sm.sendNext("Make some space in your USE inventory.")