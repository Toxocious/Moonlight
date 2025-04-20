from time import sleep

if sm.getFieldID() == 744000041:
    bossPerFloor = [
        # Floor, Boss
        [1, 9410201],  #
        [2, 9410202],  #
        [3, 9410203],  #
        [4, 9410204],  #
        [5, 9410205],  #
        [6, 9410206],  #
        [7, 9410207],  #
        [8, 9410208],  #
        [9, 9410209],  #
        [10, 9410210], #
        [11, 9410211], #
        [12, 9410212], #
        [13, 9410213], #
        [14, 9410214], #
        [15, 9410215], #
        [16, 9410216], #
        [17, 9410165], #
        [18, 9410166], #
        [19, 9410167], #
        [20, 9410168], #
        [21, 9410171], #
        [22, 9410171]  #

    ]

    def doWork(floor, boss):
        chr.chatMessage(" Stage: " + str(floor))
        sm.spawnMob(boss, 7, 257, False, 100000000)
        sm.waitForMobDeath(boss)
        sleep(3)

    for i in range(len(bossPerFloor)):
            if 0 <= i <= 20:
                doWork(bossPerFloor[i][0], bossPerFloor[i][1])
            else:
                sm.giveFriendPoints(600)
                sleep(2)
                sm.warpInstanceOut(744000020)
                sm.dispose()



else:
    sm.dispose()