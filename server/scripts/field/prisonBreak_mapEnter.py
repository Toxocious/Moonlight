# Used In All except the first map of the Escape Party Quest

from net.swordie.ms.enums import WeatherEffNoticeType

PRISON_GUARD_ANI = 9300454

stage = sm.getFieldID() % 1000 / 100

# Stage 1 is already done on  prisonBreak_1stageEnter
if stage == 2:
    sm.showWeatherNoticeToField("You must defeat all the guards. Otherwise, they will call other guard, and that is bad.", WeatherEffNoticeType.EscapePQ)
elif stage == 3:
    sm.showWeatherNoticeToField("They created a maze to keep people from entering or escaping. You must find the door that leads to the Aerial Prison!", WeatherEffNoticeType.EscapePQ)
elif stage == 4:
    sm.showWeatherNoticeToField("Defeat all the guards that are defending the door!", WeatherEffNoticeType.EscapePQ)
elif stage == 5:
    sm.showWeatherNoticeToField("This is the last obstacle. Please press on to the Aerial Prison.", WeatherEffNoticeType.EscapePQ)
elif stage == 6:
    sm.showWeatherNoticeToField("Open the prison door by defeating the guard and recovering the prison key.", WeatherEffNoticeType.EscapePQ)
elif stage == 7:
    sm.showWeatherNoticeToField("Please free us by defeating the Prison Guard!", WeatherEffNoticeType.EscapePQ)
    sm.spawnMob(PRISON_GUARD_ANI, -1281, -181, False) #Spawns the EscapePQ boss
sm.dispose()
