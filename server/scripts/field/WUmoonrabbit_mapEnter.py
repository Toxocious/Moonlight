from net.swordie.ms.enums import WeatherEffNoticeType

if not field.hasProperty("stage"):
   # field.setRespawn(False)
    mobids = [9300903, 9300904, 9300905]
    field.setNoRespawn(mobids)
    sm.killMobs(); 
    sm.setInstanceTime(1200)    
    sm.showWeatherNotice("6 Primrose Seeds, stolen by the pigs, must be recovered... ", WeatherEffNoticeType.RiceCakePQ)
    field.setProperty("stage", 1)    