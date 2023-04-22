# Root Abyss | Chaos Queen's Castle (Boss Map)
from net.swordie.ms.enums import WeatherEffNoticeType

sm.showWeatherNotice("Attempt to wake the Crimson Queen.", WeatherEffNoticeType.SnowySnowAndSprinkledFlowerAndSoapBubbles, 10000)
sm.waitForMobDeath(8920000)
if sm.hasQuest(30011):
    sm.completeQuest(30011) # [Root Abyss] Defeat the Third Seal Guardian

