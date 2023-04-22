
from time import sleep
mobsPerRound = 10

if sm.hasFieldProperty("Visitor1"):
    sm.dispose()
else:
    sm.setFieldProperty("Visitor1", True)
    for x in range(mobsPerRound):
        sm.spawnMob(9390101, 350, 32, False, 100000000000)
        sm.spawnMob(9390100, 350, 32, False, 100000000000)
        sm.spawnMob(9390101, 1340, -254, False, 100000000000)
        sm.spawnMob(9390100, 1340, -254, False, 100000000000)
        sm.spawnMob(9390101, 1328, 32, False, 100000000000)
        sm.spawnMob(9390100, 1328, 32, False, 100000000000)
        sm.spawnMob(9390101, 314, -311, False, 100000000000)
        sm.spawnMob(9390100, 314, -311, False, 100000000000)
        sleep(0.1)
