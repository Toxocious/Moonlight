from net.swordie.ms.enums import EventType
from time import sleep

sm.spawnLotus(2, 1)

sm.waitForMobDeath(8950002)
sleep(1)
sm.killMobs()
sm.addCoolDownInXDaysForParty(EventType.CLotus, 1, 1)