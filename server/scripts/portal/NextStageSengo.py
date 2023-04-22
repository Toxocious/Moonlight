currentMap = sm.getFieldID()
if sm.hasMobsInField():
    sm.chat("Kill all monsters first.")
elif currentMap / 10000 == 74400 and not currentMap == 744000000:
    sm.warp(currentMap+1, 0)

elif currentMap == 744000000:
    sm.warp(744000020, 1)
sm.dispose()
