from time import sleep
mobsPerRound = 10

if sm.hasFieldProperty("Visitor2"):
    sm.dispose()
else:
    sm.setFieldProperty("Visitor2", True)
    for x in range(mobsPerRound):
        sm.spawnMob(9390101, 1043, 32, False, 100000000000)
        sm.spawnMob(9390102, 1043, 32, False, 100000000000)
        sm.spawnMob(9390101, 785, -311, False, 100000000000)
        sm.spawnMob(9390102, 785, -311, False, 100000000000)
        sm.spawnMob(9390101, 828, -612, False, 100000000000)
        sm.spawnMob(9390102, 828, -612, False, 100000000000)
        sm.spawnMob(9390101, 1837 ,-372, False, 100000000000)
        sm.spawnMob(9390102, 1837 ,-372, False, 100000000000)
        sleep(0.1)