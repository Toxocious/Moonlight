from net.swordie.ms.enums import EventType
from time import sleep

sm.spawnLotus(2, 0)

sm.waitForMobDeath(8950002)
sleep(1)
sm.killMobs()
sm.addCoolDownInXDaysForParty(EventType.Lotus, 1, 1)