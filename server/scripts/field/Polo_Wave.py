from random import randrange
from time import sleep

respawnsPerRound = 20
Rounds = 20
x = 953
y = 165
bossY = -195
#mobs = { #wave [mobid, mobid, boss] each stage has two mobs
#    0 : [9831000, 9831001, 9831002],
#    1 : [9831003, 9831004, 9831005],
#    2 : [9831006, 9831007, 9831008],
#    3 : [5062009, 11000, 0],
#    4 : [2711004, 15000, 0],
#}

mobsBaseId = 9831000
mobsLevel = int(chr.getLevel() / 1.25)

def doWave(wave):
    count = 0
    sm.spawnBuffedMobRespawnable(mobsBaseId + wave * 3 + randrange(2), x, y, False, 0, 1 * 1000, respawnsPerRound, mobsLevel)
    while count < respawnsPerRound:
        sm.waitForMobDeath()
        count += 1
        if sm.getFieldProperty("lives") <= 0:
            sm.stopEvents()
            sm.warpInstanceOut(chr.getPreviousFieldID())
            sm.dispose()
    sleep(5)

sm.setFieldProperty("lives", 20)
for i in range(5):
    doWave(i)
sm.giveNX(100000)
sleep(1)
sm.warpInstanceOut(chr.getPreviousFieldID())