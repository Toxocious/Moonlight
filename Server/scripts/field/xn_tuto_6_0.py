# Classified Lab : Silo ; Xenon Intro Resistance Break In

CLAUDINE_FIGHT = 2159382
CLAUDINE_CAGED = 2159383
BELLE_FIGHT = 2159385
BRIGHTON_FIGHT = 2159386

sm.lockInGameUI(True)
sm.removeEscapeButton()


sm.spawnNpc(CLAUDINE_FIGHT, 300, 43)
sm.spawnNpc(BRIGHTON_FIGHT, 400, 43)
sm.spawnNpc(BELLE_FIGHT, 500, 43)

# TODO: 2 robots on the right claudine barely off screen she kills them -> move camera right showiung brighton and belle, just as we are about to see belle

sm.moveCamera(False, 1000, 43, 43)
sm.setSpeakerID(BRIGHTON_FIGHT)
sm.sendNext("They just keep coming!")
sm.setSpeakerID(CLAUDINE_FIGHT)
sm.sendSay("We heard there was a hidden lab here, but this is something big!")
sm.setSpeakerID(BELLE_FIGHT)
sm.sendSay("With defenses like these, they must be hiding something big. And I'm going to find out what.")
sm.setSpeakerID(CLAUDINE_FIGHT)
sm.sendSay("You're cool as can be, aren't you Belle? Nothing fazes you.")
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg1/2", 2000, CLAUDINE_FIGHT)
sm.sendDelay(2000)

sm.flipNpcByTemplateId(CLAUDINE_FIGHT, False)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/12", 2000, CLAUDINE_FIGHT)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 2000, BRIGHTON_FIGHT)
# sm.sendDelay(2000)

sm.moveNpcByTemplateId(BRIGHTON_FIGHT, False, 50, 100)
sm.sendDelay(1000)

sm.moveNpcByTemplateId(BRIGHTON_FIGHT, True, 25, 100)
sm.removeNpc(CLAUDINE_FIGHT)
sm.spawnNpc(CLAUDINE_CAGED, 300, 43)
sm.sendDelay(30)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/13", 2000, BELLE_FIGHT)
sm.sendDelay(2000)
sm.forcedInput(2)  # Interrupted by InGameDirectionResponse Event
