if not sm.hasMobsInField():
    sm.spawnMob(9101078, 0, 353, False)
sm.createStopWatch(30) # 30 sec
sm.invokeAfterDelay(30000, "warpInstanceOut", 993000600, 0)