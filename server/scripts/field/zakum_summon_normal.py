sm.spawnMob(8800002, -54, 86, False)
sm.showZakumPlatforms()

for i in range(8):
    sm.spawnMob(8800003 + i, -54, 86, False)

if sm.waitForMobDeath(8800002):
    sm.killMobs()
    sm.dispose()

