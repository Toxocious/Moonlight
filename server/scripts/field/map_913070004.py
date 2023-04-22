# Manual Labor
if not sm.hasQuest(20034) or not sm.hasQuestCompleted(20034):
    sm.lockInGameUI(True)  
    sm.chatScript("Mr. Limbert's General Store")
    sm.chatScript("Month 3, Day 11")
    sm.doEventAndSendDelay(1000, "forcedInput", 2)

    sm.doEventAndSendDelay(1000, "forcedInput", 1)

    sm.doEventAndSendDelay(2000, "showBalloonMsg", "Effect/Direction7.img/effect/tuto/step0/5", 2000)

    sm.doEventAndSendDelay(500, "forcedInput", 2)

    sm.forcedInput(0)
    sm.doEventAndSendDelay(1000, "showBalloonMsg", "Effect/Direction7.img/effect/tuto/step0/6", 2000)

    sm.doEventAndSendDelay(1000, "showBalloonMsg", "Effect/Direction7.img/effect/tuto/step0/4", 2000)

    sm.doEventAndSendDelay(2000, "showBalloonMsg", "Effect/Direction7.img/effect/tuto/step0/7", 2000)

    sm.doEventAndSendDelay(1000, "showBalloonMsg", "Effect/Direction7.img/effect/tuto/step0/8", 2000)

    sm.chatScript("Someone suspicious is in the back yard...")
    sm.lockInGameUI(False)  
    sm.startQuestNoCheck(20034)   