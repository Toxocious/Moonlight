# Classified Lab : Laboratory ; Upper Floor Before Break In

GELIMER_PRESENT = 2159377

sm.lockInGameUI(True)
sm.removeEscapeButton()
sm.spawnNpc(GELIMER_PRESENT, -170, 0)
sm.flipNpcByTemplateId(GELIMER_PRESENT, False)
sm.sendDelay(1000)

sm.moveCamera(False, 1000, 0, -25)
sm.setSpeakerID(GELIMER_PRESENT)
sm.sendNext("This is the last phase of testing. I know it's been a long time... are you ready?")
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/11", 2000, GELIMER_PRESENT)
sm.sendDelay(2000)
sm.moveCamera(True, 1000, 0, 0)
sm.spawnMob(9300635, 166, -301, False)
sm.lockInGameUI(False)
sm.startQuestNoCheck(23600)
