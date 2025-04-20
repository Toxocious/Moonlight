# Root Abyss - Temporal Crevasse (Von Bon's Map)
from net.swordie.ms.enums import WeatherEffNoticeType

sm.showWeatherNotice("Summon Von Bon in the Dimensional Schism.", WeatherEffNoticeType.SnowySnowAndSprinkledFlowerAndSoapBubbles, 10000)
if sm.hasQuest(3009):
    sm.waitForMobDeath(9303154)
    sm.completeQuest(30009) #[Root Abyss] Defeat the First Guardian