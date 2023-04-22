# 5th job portal inside Henesys

if sm.hasQuest(1461):
    if sm.getFieldID() == 100000101:
        sm.warpInstanceIn(450000100)
        sm.setInstanceTime(10, 450000000)
    elif sm.getFieldID() == 450000000:
        sm.warpInstanceOut(100000201)
    elif sm.getFieldID() == 400000001:
        sm.warpInstanceIn(450000110)
        sm.setInstanceTime(10, 450000010)
    elif sm.getFieldID() == 450000010:
        sm.warpInstanceOut(400000001)
    elif sm.getFieldID() == 105300000:
        sm.warpInstanceIn(450000120)
        sm.setInstanceTime(10, 450000020)
    elif sm.getFieldID() == 450000020:
        sm.warpInstanceOut(105300000)