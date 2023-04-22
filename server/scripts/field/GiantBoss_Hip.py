abdomenID=9390612;
if not sm.golluxMapAlreadyVisited():
    sm.spawnMob(abdomenID)
if not sm.hasMobsInField():
    sm.openGolluxPortal("change", 7)
    sm.openGolluxPortal("clear1", 12)
    sm.openGolluxPortal("clear2", 12)
sm.addCurrentGolluxMap()
sm.waitForMobDeath(abdomenID)
sm.addClearedGolluxMap()
sm.openGolluxPortal("change", 7)
sm.openGolluxPortal("clear1", 12)
sm.openGolluxPortal("clear2", 12)