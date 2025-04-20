from time import sleep

respawnsPerRound = 20
Rounds = 20

sm.setPyramidPQLives(20)

for x in range(1, Rounds + 1):
    sm.setPyramidPQWave(x)
    sm.startPyramidPQWave(x, respawnsPerRound)
    while sm.hasMobsInField():
        sm.waitForMobDeath()
        sleep(3)
        if sm.getPyramidPQLives() <= 0:
            sm.stopEvents()
            sm.warpInstanceOut(910002000)
            sm.dispose()
    sleep(2)
sm.giveNX(200000)
sm.giveItem(4310212, 2)
sleep(1)
sm.warpInstanceOut(910002000)