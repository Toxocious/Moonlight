# Manual Labor

# Constants
CYGNUS = 1106001
JAAH = 1106005

if not sm.hasQuestCompleted(20034):
    sm.lockInGameUI(True)  
    sm.chatScript("General Store Yard")
    sm.forcedInput(2)
    sm.showBalloonMsg("Effect/Direction7.img/effect/tuto/step0/4", 2000)
    sm.sendDelay(2000)

    sm.localEmotion(6, 10000, False)

    sm.removeEscapeButton()
    sm.setSpeakerID(JAAH)
    sm.setPlayerAsSpeaker()
    sm.sendNext("Huh? Who's that girl?")

    sm.forcedInput(2)