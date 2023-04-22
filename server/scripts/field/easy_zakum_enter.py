sm.spawnMob(8800022, -54, 86, False)
sm.showZakumPlatforms()

for i in range(8):
    sm.spawnMob(8800023 + i, -54, 86, False)

if sm.waitForMobDeath(8800022):
    sm.killMobs()
    sm.dispose()
