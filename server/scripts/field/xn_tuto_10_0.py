# Classified Lab : Silo ; Xenon Intro Escaping With Claudine

ROO_D_AGGRESIVE = 2159381
CLAUDINE_ARM_SLING = 2159384

sm.lockInGameUI(True)

sm.forcedInput(2)
sm.sendDelay(30)

sm.forcedInput(0)
sm.sendDelay(30)

sm.forcedInput(2)
sm.showEffect("Effect/Direction12.img/effect/tuto/sig", 0, 0, 0)
sm.showEffect("Effect/Direction12.img/effect/tuto/luti", 0, 0, 0)
sm.sendDelay(2600)

sm.moveCamera(False, 1000, -436, 43)
sm.showBalloonMsg("Effect/Direction12.img/effect/tuto/BalloonMsg1/0", 900)
sm.sendDelay(1200)

sm.showBalloonMsg("Effect/Direction12.img/effect/tuto/BalloonMsg2/15", 1200)
sm.forcedInput(0)
sm.sendDelay(1200)

sm.showEffect("Effect/Direction12.img/effect/tuto/laser", 0, 0, 0)
sm.sendDelay(1800)

sm.showEffectOnPosition("Effect/Direction12.img/effect/tuto/BalloonMsg2/16", 1200, -426, -100)
sm.sendDelay(1200)

sm.lockInGameUI(False)
sm.warp(931050990)
