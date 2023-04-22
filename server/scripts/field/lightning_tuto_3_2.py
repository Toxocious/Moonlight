ARAN = 2159356

sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg0/7", 4000, 0, -100, -2, -2, False, 0)
sm.sendDelay(1500)

sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg0/6", 2500, 0, -100, 0, sm.getNpcObjectIdByTemplateId(ARAN), False, 0)
sm.sendDelay(1500)

sm.lockInGameUI(False)
sm.removeNpc(ARAN)
sm.warp(927020060, 0)