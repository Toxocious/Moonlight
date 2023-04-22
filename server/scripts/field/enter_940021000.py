# DIPQ

import random
from time import sleep

sleep(4)
mobsPerRound = 50
mobsLevel = chr.getLevel()
mobHp = 500000
firstWaveMobs = [9300618,9300619]
fourthWaveMobs = [9300620,9300621]

if sm.getFieldID() == 940021000:

    def firstWave():
        for x in range(mobsPerRound):
            sm.spawnMobByFieldID(random.choice(firstWaveMobs), 2604, 29, False, mobHp, 940021000)
            sleep(0.1)
            if not sm.getFieldID() == 940021000:
                sm.dispose()
        while sm.hasMobsInField():
            sleep(5)


    def secondWave():
        sm.spawnMobByFieldID(9300622, 2604, 29, False, 25000000, 940021000)
        sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def thirdWaveTOP():
        for x in range(mobsPerRound):
            sm.spawnMobByFieldID(9300625, 2030, -451, False, mobHp, 940021000)
            sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def thirdWaveMID():
        for x in range(mobsPerRound):
            sm.spawnMobByFieldID(9300625, 2604, 29, False, mobHp, 940021000)
            sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def thirdWaveRIGHT():
        for x in range(mobsPerRound):
            sm.spawnMobByFieldID(9300625, 3250, 29, False, mobHp, 940021000)
            sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def fourthWave():
        for x in range(mobsPerRound):
            sm.spawnMobByFieldID(random.choice(fourthWaveMobs), 2015, 0, False, mobHp, 940021000)
            sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def fifthWave():
        sm.spawnMobByFieldID(9300634, 2604, 29, False, 25000000, 940021000)
        sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def BossWave():
        sm.spawnMobByFieldID(9300627, 2604, 29, False, 940021000)
        sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def checkMap():
        if not sm.getFieldID() == 940021000:
            sm.dispose()

    for x in range(6):
        checkMap()
        firstWave()
    sm.showClearStageExpWindow(sm.getPQExp())
    sm.giveExp(sm.getPQExp())
    sleep(5)

    checkMap()
    secondWave()
    sm.showClearStageExpWindow(sm.getPQExp())
    sm.giveExp(sm.getPQExp())
    sleep(5)

    waves = [thirdWaveTOP, thirdWaveMID, thirdWaveRIGHT]
    for x in range(10):
        function = random.choice(waves)
        function()
    sm.showClearStageExpWindow(sm.getPQExp())
    sm.giveExp(sm.getPQExp())
    sleep(5)

    for x in range(6):
        checkMap()
        fourthWave()
    sm.showClearStageExpWindow(sm.getPQExp())
    sm.giveExp(sm.getPQExp())
    sleep(5)

    checkMap()
    fifthWave()
    sm.showClearStageExpWindow(sm.getPQExp())
    sm.giveExp(sm.getPQExp())
    sleep(5)
    sm.giveItem(2431127)

    checkMap()
    BossWave()
    sm.showClearStageExpWindow(sm.getPQExp())
    sm.giveExp(sm.getPQExp())
    sm.giveItem(2431128)
    sleep(5)

    sm.warpInstanceOutParty(940020000)