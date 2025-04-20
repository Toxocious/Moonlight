from net.swordie.ms.enums import WeatherEffNoticeType
from time import sleep
if chr.getInstance() is not None:
    QueenId = 8920100
    if field.hasProperty("QueenSummoned"):
        sm.removeReactor()
        field.setProperty("QueenSummoned", False)
        sm.dispose()
    reactor.incHitCount()
    sm.showWeatherNotice("Please allow me to mourn over your imminent demise.", WeatherEffNoticeType.BossCrimsonQueenCrownPink, 10000)
    if reactor.getHitCount() == 5:
        sm.spawnMob(QueenId, 37, 135, False)
        sm.removeReactor()
        field.setProperty("QueenSummoned", True)
        sleep(2)
        sm.removeReactor()
        sm.removeReactor()