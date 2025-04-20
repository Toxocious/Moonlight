if not sm.hasMobsInField():
    fieldID = sm.getFieldID()
    map = fieldID + 100
    portal = 0
    sm.warp(map, portal)
else:
    sm.chat("Please kill all monsters first.")
sm.dispose()
