disembodiedVonBon = 8910001
sm.spawnMob(disembodiedVonBon)
sm.createTimerGauge(15)
sm.invokeAfterDelay(30000, "warp", 105200510)
sm.waitForMobDeath(disembodiedVonBon)
sm.warp(105200510)
sm.stopEvents()