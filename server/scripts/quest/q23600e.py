# Testing Testing One Two Three ; Xenon Intro Quest to kill Fabricated Von Leon

GELIMER_PRESENT = 2159377

sm.removeEscapeButton()
sm.lockInGameUI(True)
sm.moveCamera(False, 1000, 0, -25)
sm.setSpeakerID(GELIMER_PRESENT)
sm.removeEscapeButton()
sm.sendNext("Good, very good! I am very satisfied with these results. Just a few more fine adjustments and...")
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg1/0", 2000, GELIMER_PRESENT)
sm.flipNpcByTemplateId(GELIMER_PRESENT, True)
sm.sendNext("An intruder?! It could be Orchid. Turn on the monitor!")
# monitor flashes a couple times, shows resistance breaking in -> delay -> shows close up of resistance
sm.sendNext("Is it the Resistance? I suppose that would be better than Orchid, but... this is the worst possible time!")
sm.sendSay("Wait, wait, wait. Maybe this will work. One more test, yes... they will be perfect... Hahaha... MWAHAHAHA!")
sm.warpInstanceIn(931050940)
sm.removeNpc(GELIMER_PRESENT)
sm.completeQuest(parentID)
