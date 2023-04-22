# aswan exit | Azwan Obelisk : Azwan
oldFieldID = sm.getReturnField()
if oldFieldID == 0 or oldFieldID == 910000000:
    sm.chat("(Portal) Cannot find your previous map ID, warping to Henesys.")
    map = 100000000
    portal = 0
else:
    map = oldFieldID
    portal = 0
sm.warp(map, portal)
sm.dispose()
