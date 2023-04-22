# 940200000
sm.lockInGameUI(True)
sm.hideUser(True)
sm.forcedInput(0)
if "2" in sm.getQRValue(38900):
    sm.showFieldEffect("Map/Effect2.img/eunwol/gofoxvillage")
    sm.sendDelay(3000)

    sm.hideUser(False)
    sm.warp(940200010, 0)
elif "1" in sm.getQRValue(38900):
    sm.sendDelay(3000)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("......")
    sm.sendSay("Did I...die?")
    sm.sendSay("......")
    sm.sendSay("I guess there is no afterlife. Freud will be pretty disappointed.")
    sm.sendSay("......")
    sm.sendDelay(2000)

    sm.showEffect("Effect/Direction15.img/effect/story/BalloonMsg1/0", 3200, 300, -210, -2, -2, False, 0)
    sm.sendDelay(300)

    sm.showEffect("Effect/Direction15.img/effect/story/BalloonMsg1/1", 2900, -300, -70, -2, -2, False, 0)
    sm.sendDelay(300)

    sm.showEffect("Effect/Direction15.img/effect/story/BalloonMsg1/2", 2600, 300, 0, -2, -2, False, 0)
    sm.sendDelay(300)

    sm.showEffect("Effect/Direction15.img/effect/story/BalloonMsg1/3", 2300, -300, 70, -2, -2, False, 0)
    sm.sendDelay(300)

    sm.showEffect("Effect/Direction15.img/effect/story/BalloonMsg1/4", 2000, 300, 210, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.sendNext("What's that sound?")
    sm.sendDelay(500)

    sm.showEffect("Effect/Direction15.img/effect/story/BalloonMsg1/5", 3200, -300, -70, -2, -2, False, 0)
    sm.sendDelay(300)

    sm.showEffect("Effect/Direction15.img/effect/story/BalloonMsg1/6", 2900, 300, -210, -2, -2, False, 0)
    sm.sendDelay(300)

    sm.showEffect("Effect/Direction15.img/effect/story/BalloonMsg1/7", 2600, -300, 70, -2, -2, False, 0)
    sm.sendDelay(300)

    sm.showEffect("Effect/Direction15.img/effect/story/BalloonMsg1/8", 2300, 300, 0, -2, -2, False, 0)
    sm.sendDelay(300)

    sm.showEffect("Effect/Direction15.img/effect/story/BalloonMsg1/9", 2000, 300, 210, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.sendNext("It's getting loud. Maybe it's them. Maybe I'm not ready to go to the afterlife just yet.")

    sm.blind(1, 250, 240, 240, 240, 3000)
    sm.sendDelay(3000)

    sm.blind(0, 0, 0, 1000)
    sm.sendDelay(1000)

    sm.showFieldEffect("Map/Effect2.img/eunwol/meetfox")
    sm.sendDelay(3200)
    sm.levelUntil(10)

    sm.hideUser(False)
    sm.warp(940200010, 0)