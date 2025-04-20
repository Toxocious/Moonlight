fields = {
100000201 : [100000205, 2],
103000003 : [103000008, 1],
102000003 : [102000004, 1],
101000003 : [101000004, 2],
# 120000101 : [100000205, 2],
}

currentMap = sm.getFieldID()
if currentMap == 120000101:# Pirates
    sm.chatBlue("[WIP] no MapID for Hall of Pirates")
elif currentMap in fields:
    sm.warp(fields[currentMap][0], fields[currentMap][1])
else:
    sm.chat("This script (rankRoom.py) has not been coded for the given map (" + str(currentMap) + ")")
sm.dispose()
