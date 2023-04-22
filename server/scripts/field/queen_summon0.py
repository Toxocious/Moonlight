# Root Abyss | Queen's Castle (Boss Map)
from net.swordie.ms.enums import WeatherEffNoticeType

queenNormalChestId = 8920106

sm.showWeatherNotice("Attempt to wake the Crimson Queen.", WeatherEffNoticeType.SnowySnowAndSprinkledFlowerAndSoapBubbles, 10000)
sm.waitForMobDeath(8920100)
sm.spawnMob(queenNormalChestId, 37, 135, False)
if sm.hasQuest(30011):
    sm.completeQuest(30011) # [Root Abyss] Defeat the Third Seal Guardian
