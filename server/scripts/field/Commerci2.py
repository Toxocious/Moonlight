# id 865000100 (Trade : En Route), field 865000100
import random
from random import randrange
from time import sleep

sleep(1)
mobsPerRound = 75
mobsLevel = 220
mobs = [9390800, 9390801]
runners = [9390802, 9390803]

if sm.getFieldID() == 865000200:
    def normalWave():
        position = sm.getReactorPositionByPos(randrange(1))
        for x in range(mobsPerRound):
            sm.spawnMob(random.choice(mobs), position.getX(), position.getY(), False, 52000000)
            sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def bossWave():
        xOffSet = 0
        position = sm.getReactorPositionByPos(2)
        yOffSet = 100
        sm.spawnMob(9390857, position.getX() + xOffSet, position.getY() + yOffSet, False, 84000000)
        while sm.hasMobsInField():
            sleep(5)

    def runnerWave():
        boss = random.choice(runners)
        position = sm.getReactorPositionByPos(2)
        xOffSet = 500
        sm.spawnMob(boss, position.getX() + xOffSet, position.getY(), False, 52000000)
        while sm.hasMobsInField():
            sleep(5)


    normalWave()

    waves = [normalWave, runnerWave]
    for x in range(3): #three rounds either normal or runners
        function = random.choice(waves)
        function()

    bossWave()

    sm.giveShipExp(15)
    sm.giveItem(4310100, 6)
    sm.warpInstanceOut(865000001)

if sm.getFieldID() == 865000900:
    def normalWave():
        position = sm.getReactorPositionByPos(randrange(1))
        for x in range(mobsPerRound):
            sm.spawnMob(random.choice(mobs), position.getX(), position.getY(), False, 52000000)
            sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def bossWave():
        xOffSet = 0
        yOffSet = 100
        position = sm.getReactorPositionByPos(2)
        sm.spawnMob(9390857, position.getX() + xOffSet, position.getY() + yOffSet, False, 3820000000)
        while sm.hasMobsInField():
            sleep(5)

    def runnerWave():
        boss = random.choice(runners)
        position = sm.getReactorPositionByPos(2)
        xOffSet = 500
        sm.spawnMob(boss, position.getX() + xOffSet, position.getY(), False, 52000000)
        while sm.hasMobsInField():
            sleep(5)


    normalWave()

    waves = [normalWave, runnerWave]
    for x in range(3): #three rounds either normal or runners
        function = random.choice(waves)
        function()

    bossWave()

    sm.giveShipExp(20)
    sm.giveItem(4310100, 18)
    sm.warpInstanceOut(865000001)

if sm.getFieldID() == 865000300:
    def normalWave():
        position = sm.getReactorPositionByPos(randrange(1))
        for x in range(mobsPerRound):
            sm.spawnMob(random.choice(mobs), position.getX(), position.getY(), False, 52000000)
            sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def bossWave():
        position = sm.getReactorPositionByPos(2)
        xOffSet = 500
        yOffSet = 100
        sm.spawnMob(9390804, position.getX() + xOffSet, position.getY() + yOffSet, False, 3070000000)
        while sm.hasMobsInField():
            sleep(5)

    def runnerWave():
        boss = random.choice(runners)
        position = sm.getReactorPositionByPos(2)
        xOffSet = 500
        sm.spawnMob(boss, position.getX() + xOffSet, position.getY(), False, 52000000)
        while sm.hasMobsInField():
            sleep(5)


    normalWave()

    waves = [normalWave, runnerWave]
    for x in range(3): #three rounds either normal or runners
        function = random.choice(waves)
        function()

    bossWave()

    sm.giveShipExp(25)
    sm.giveItem(4310100, 38)
    sm.warpInstanceOut(865000001)

if sm.getFieldID() == 865000100:
    def normalWave():
        position = sm.getReactorPositionByPos(randrange(1))
        for x in range(mobsPerRound):
            sm.spawnMob(random.choice(mobs), position.getX(), position.getY(), False, 230000000)
            sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def bossWave():
        position = sm.getReactorPositionByPos(2)
        xOffSet = 500
        yOffSet = 100
        sm.spawnMob(9390804, position.getX() + xOffSet, position.getY() + yOffSet, False, 243000000000)
        while sm.hasMobsInField():
            sleep(5)

    def runnerWave():
        boss = random.choice(runners)
        position = sm.getReactorPositionByPos(2)
        xOffSet = 500
        sm.spawnMob(boss, position.getX() + xOffSet, position.getY(), False, 230000000)
        while sm.hasMobsInField():
            sleep(5)


    normalWave()

    waves = [normalWave, runnerWave]
    for x in range(3): #three rounds either normal or runners
        function = random.choice(waves)
        function()

    bossWave()

    sm.giveShipExp(25)
    sm.giveItem(4310100, 66)
    sm.warpInstanceOut(865000001)

if sm.getFieldID() == 865000400:
    def normalWave():
        position = sm.getReactorPositionByPos(randrange(1))
        for x in range(mobsPerRound):
            sm.spawnMob(random.choice(mobs), position.getX(), position.getY(), False, 2300000000)
            sleep(0.1)
        while sm.hasMobsInField():
            sleep(5)

    def bossWave():
        position = sm.getReactorPositionByPos(2)
        xOffSet = 500
        yOffSet = 100
        sm.spawnMob(9390868, position.getX() + xOffSet, position.getY() + yOffSet, False, 77600000000)
        while sm.hasMobsInField():
            sleep(5)

    def runnerWave():
        boss = random.choice(runners)
        position = sm.getReactorPositionByPos(2)
        xOffSet = 500
        sm.spawnMob(boss, position.getX() + xOffSet, position.getY(), False, 2300000000)
        while sm.hasMobsInField():
            sleep(5)


    normalWave()

    waves = [normalWave, runnerWave]
    for x in range(3): #three rounds either normal or runners
        function = random.choice(waves)
        function()

    bossWave()

    sm.giveShipExp(30)
    sm.giveItem(4310100, 102)
    sm.warpInstanceOut(865000001)