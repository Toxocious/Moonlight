leftShoulderID=9390611;
if not sm.golluxMapAlreadyVisited():
    sm.spawnMob(leftShoulderID)
if not sm.hasMobsInField():
    sm.openGolluxPortal("clear", 7)
    sm.openGolluxPortal("phase3", 12)
sm.addCurrentGolluxMap()
sm.waitForMobDeath(leftShoulderID)
sm.addClearedGolluxMap()
sm.openGolluxPortal("clear", 7)
sm.openGolluxPortal("phase3", 12)