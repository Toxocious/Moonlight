# Classified Lab : Laboratory ; Xenon Intro Abduction Result

GELIMER_PRESENT = 2159377

sm.spawnNpc(GELIMER_PRESENT, -100, 0)
sm.flipNpcByTemplateId(GELIMER_PRESENT, False)
sm.lockInGameUI(True)

sm.teleportInField(0, 0)
sm.showEffect("Effect/Direction12.img/effect/tuto/doorOpen", 0, 0, 0)

sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/8", 2000, GELIMER_PRESENT)
sm.sendDelay(2000)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/9", 2000, GELIMER_PRESENT)
sm.sendDelay(2000)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/10", 2000, GELIMER_PRESENT)
sm.sendDelay(2000)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/8", 2000, GELIMER_PRESENT)
sm.sendDelay(2000)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/7", 2000, GELIMER_PRESENT)
sm.sendDelay(2000)

sm.warp(931060081)
sm.lockInGameUI(False)
sm.removeNpc(GELIMER_PRESENT)
