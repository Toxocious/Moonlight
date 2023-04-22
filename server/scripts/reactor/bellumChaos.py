from net.swordie.ms.enums import WeatherEffNoticeType
if chr.getInstance() is not None:
    sm.removeReactor()
    sm.invokeAfterDelay(1500, "spawnMob", 8930000, -200, 440, False)
    sm.showWeatherNotice("You ignore my warnings?! I will show you no mercy!", WeatherEffNoticeType.BossVellum, 10000)