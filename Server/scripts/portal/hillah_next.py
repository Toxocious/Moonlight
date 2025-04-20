if not sm.hasMobsInField(chr.getFieldID()):
    if sm.getFieldID() == 262030100:
        sm.warp(262030200, True)
    elif sm.getFieldID() == 262030200:
        sm.warp(262030300, True)
    elif sm.getFieldID() == 262031100:
        sm.warp(262031200, True)
    elif sm.getFieldID() == 262031200:
        sm.warp(262031300, True)
else:
    sm.chatRed("You must eliminate all the monsters in the map before continuing")