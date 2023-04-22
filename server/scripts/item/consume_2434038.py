if sm.canHold(1672069):
    if sm.canHold(1662073):
        sm.giveItem(1662073)
        sm.giveItem(1672069)
        sm.consumeItem(2434038)
    else:  sm.sendOkay("Please check that you have room in your inventory.")
else:  sm.sendOkay("Please check that you have room in your inventory.")