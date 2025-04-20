sm.addCurrentGolluxMap()
if sm.getAmountOfMobsInField() < 2:
    sm.addClearedGolluxMap()
else:
    while sm.getAmountOfMobsInField() > 1:
        sm.waitForMobDeath()
    sm.addClearedGolluxMap()
    sm.openGolluxPortal("open", 1)
    sm.openGolluxPortal("clear", 1)