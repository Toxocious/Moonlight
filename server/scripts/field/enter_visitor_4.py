from time import sleep
mobsPerRound = 10

if sm.hasFieldProperty("Visitor4"):
    sm.dispose()
else:
    sm.setFieldProperty("Visitor4", True)
    for x in range(mobsPerRound):
        sm.spawnMob(9390104, 165, 32, False, 100000000000)
        sm.spawnMob(9390103, 165, 32, False, 100000000000)
        sm.spawnMob(9390104, 945, -719, False, 100000000000)
        sm.spawnMob(9390103, 945, -719, False, 100000000000)
        sm.spawnMob(9390104, 498, -345, False, 100000000000)
        sm.spawnMob(9390103, 498, -345, False, 100000000000)
        sm.spawnMob(9390104, 1221, 32, False, 100000000000)
        sm.spawnMob(9390103, 1221, 32, False, 100000000000)
        sleep(0.1)