# Shaolin Temple : Sutra Depository - Top

from net.swordie.ms.enums import WeatherEffNoticeType

CHIEF_PRIEST = 9601068 # mob id
ZHEUNG_GUAN = 9310046 # Npc id
time = 30 *60

sm.setInstanceTime(time, 701220601, 0)
sm.chatScript("I told you not to follow me..")

sm.invokeAfterDelay(3000, "showWeatherNotice", "Now, you will be my first victim!", WeatherEffNoticeType.BossChiefPriest)
sm.invokeAfterDelay(4000, "spawnMob", CHIEF_PRIEST, 150, 303, False)

sm.waitForMobDeath(CHIEF_PRIEST)
sm.showWeatherNotice("Muahaha.... You got lucky.. This time.", WeatherEffNoticeType.BossChiefPriest)
sm.invokeAfterDelay(10000, "warpInstanceOut", 701220601, 0)
if not sm.hasQuestCompleted(62022) and not sm.hasQuest(62022):
    sm.startQuestNoCheck(62022)
    sm.removeEscapeButton()
    sm.setBoxChat()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("What just happened?..\r\n"
                "I should speak with #p"+ str(ZHEUNG_GUAN) +"#.")