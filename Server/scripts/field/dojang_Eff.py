# Mu Lung Dojo

import time
from net.swordie.ms.constants import GameConstants
from net.swordie.ms.constants import WzConstants
from time import sleep

EASY = 1       # Easy   Bosses  1 - 10  Map: 925070100
NORMAL = 2     # Normal Bosses  1 - 20  Map: 925070200
HARD = 3       # Hard   Bosses 21 - 30  Map: 925070300
HELL = 4       # Hell   Bosses 21 - 30  Map: 925070400
CHAOS = 5      # Chaos  Bosses 21 - 40  Map: 925070500
CRAZY = 6      # Crazy  Bosses 21 - 40  Map: 925070600

mode = ((sm.getFieldID() % 10000) / 100)
startTime = int(round(time.time()))

# Floor, Boss, hp

bossPerEasyFloor = [

    # Easy Mode
    [1, 9305300, 10000000],
    [2, 9305301, 11000000],
    [3, 9305302, 12000000],
    [4, 9305303, 13000000],
    [5, 9305304, 14000000],
    [6, 9305305, 15000000],
    [7, 9305306, 16000000],
    [8, 9305307, 17000000],
    [9, 9305308, 18000000],
    [10, 9305309, 19000000]
]

bossPerNormalFloor = [

    # Normal Mode
    [1, 9305300, 100000000],
    [2, 9305301, 110000000],
    [3, 9305302, 120000000],
    [4, 9305303, 130000000],
    [5, 9305304, 140000000],
    [6, 9305305, 150000000],
    [7, 9305306, 160000000],
    [8, 9305307, 170000000],
    [9, 9305308, 180000000],
    [10, 9305309, 190000000],
    [11, 9305310, 200000000],
    [12, 9305311, 210000000],
    [13, 9305312, 220000000],
    [14, 9305313, 230000000],
    [15, 9305314, 240000000],
    [16, 9305315, 250000000],
    [17, 9305316, 260000000],
    [18, 9305317, 270000000],
    [19, 9305318, 280000000],
    [20, 9305319, 290000000]
]

bossPerHardFloor = [

    # Hard Mode
    [1, 9305310, 500000000],
    [2, 9305311, 510000000],
    [3, 9305312, 520000000],
    [4, 9305313, 530000000],
    [5, 9305314, 540000000],
    [6, 9305315, 550000000],
    [7, 9305316, 560000000],
    [8, 9305317, 570000000],
    [9, 9305318, 580000000],
    [10, 9305319, 590000000],
    [11, 9305320, 600000000],
    [12, 9305321, 610000000],
    [13, 9305322, 620000000],
    [14, 9305323, 630000000],
    [15, 9305324, 640000000],
    [16, 9305325, 650000000],
    [17, 9305326, 660000000],
    [18, 9305327, 670000000],
    [19, 9305328, 680000000],
    [20, 9305329, 690000000]
]

bossPerHellFloor = [

    # Hell Mode
    [1, 9305320, 2010000000],
    [2, 9305321, 2200000000],
    [3, 9305322, 2400000000],
    [4, 9305323, 2600000000],
    [5, 9305324, 2800000000],
    [6, 9305325, 3000000000],
    [7, 9305326, 3200000000],
    [8, 9305327, 3400000000],
    [9, 9305328, 3600000000],
    [10, 9305329, 3800000000]
]

bossPerChaosFloor = [

    # Chaos Mode
    [1, 9305320, 5000000000],
    [2, 9305321, 5250000000],
    [3, 9305322, 5500000000],
    [4, 9305323, 5750000000],
    [5, 9305324, 6000000000],
    [6, 9305325, 6250000000],
    [7, 9305326, 6500000000],
    [8, 9305327, 6750000000],
    [9, 9305328, 7000000000],
    [10, 9305329, 7250000000],
    [11, 9305330, 7500000000],
    [12, 9305331, 7750000000],
    [13, 9305332, 8000000000],
    [14, 9305333, 8250000000],
    [15, 9305334, 8500000000],
    [16, 9305336, 8750000000],
    [17, 9305337, 9000000000],
    [18, 9305338, 9250000000],
    [19, 9305339, 9500000000],
]

bossPerCrazyFloor = [

    # Crazy Mode
    [1, 9305320, 25000000000],
    [2, 9305321, 27500000000],
    [3, 9305322, 27500000000],
    [4, 9305323, 30000000000],
    [5, 9305324, 30000000000],
    [6, 9305325, 32500000000],
    [7, 9305326, 32500000000],
    [8, 9305327, 35000000000],
    [9, 9305328, 35000000000],
    [10, 9305329, 37500000000],
    [11, 9305330, 37500000000],
    [12, 9305331, 40000000000],
    [13, 9305332, 40000000000],
    [14, 9305333, 42500000000],
    [15, 9305334, 42500000000],
    [16, 9305335, 45000000000],
    [17, 9305336, 45000000000],
    [18, 9305337, 47500000000],
    [19, 9305338, 47500000000],
    [20, 9305339, 50000000000]
]

def checkMap():
    if not sm.getFieldID() >= 925070100 and sm.getFieldID() <= 925070600:
        sm.dispose()

def doWork(floor, boss, hp):
        sm.showEffectToField("" + WzConstants.EFFECT_DOJO_STAGE_NUMBER + "" + str(floor))
        sm.showEffectToField(WzConstants.EFFECT_DOJO_STAGE)
        checkMap()
        sm.spawnMob(boss, 1, 7, False, hp)
        checkMap()
        sm.waitForMobDeath(boss)
        sm.showEffectToField(WzConstants.EFFECT_DOJO_CLEAR)
        sleep(GameConstants.DOJO_SPAWM_BOSS_DELAY)

if mode == EASY:
    for i in range(len(bossPerEasyFloor)):
        if 0 <= i <= 9:
            doWork(bossPerEasyFloor[i][0], bossPerEasyFloor[i][1], bossPerEasyFloor[i][2])

elif mode == NORMAL:
    for i in range(len(bossPerNormalFloor)):
        if 1 <= i <= 19:
            doWork(bossPerNormalFloor[i][0], bossPerNormalFloor[i][1], bossPerNormalFloor[i][2])

elif mode == HARD:
    for i in range(len(bossPerHardFloor)):
        if 1 <= i <= 19:
            doWork(bossPerHardFloor[i][0], bossPerHardFloor[i][1], bossPerHardFloor[i][2])

elif mode == HELL:
    for i in range(len(bossPerHellFloor)):
        if 1 <= i <= 9:
            doWork(bossPerHellFloor[i][0], bossPerHellFloor[i][1], bossPerHellFloor[i][2])

elif mode == CHAOS:
    for i in range(len(bossPerChaosFloor)):
        if 0 <= i <= 19:
            doWork(bossPerChaosFloor[i][0], bossPerChaosFloor[i][1], bossPerChaosFloor[i][2])

elif mode == CRAZY:
    for i in range(len(bossPerCrazyFloor)):
        if 0 <= i <= 19:
            doWork(bossPerCrazyFloor[i][0], bossPerCrazyFloor[i][1], bossPerCrazyFloor[i][2])


sm.completeSDCForParty()
sm.chat("You have completed Mu Lung Dojo in " + str(int(round(time.time())) - startTime) + " seconds.")
sm.warpInstanceOutParty(925020001)