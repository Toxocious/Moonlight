GELIMER_PRESENT = 2159377
BERYL_FORCEMOVE = 2159378
BERYL = 2159379
CLAUDINE_CAGED = 2159383
BELLE_FIGHT = 2159385
BRIGHTON_FIGHT = 2159386

isFemale = chr.getAvatarData().getAvatarLook().getGender()

sm.removeEscapeButton()

sm.forcedInput(0)
sm.setPlayerAsSpeaker()
sm.sendNext("Ready to attack on your command, Gelimer.")
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg1/2", 2000, CLAUDINE_CAGED)
sm.sendDelay(2000)
sm.setSpeakerID(BELLE_FIGHT)
sm.sendNext("Get away from Claudine!")
sm.showBalloonMsg("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 2000)
sm.sendDelay(2000)
sm.setPlayerAsSpeaker()
sm.sendNext("Argh, M... My head! It... hurts.")
sm.showBalloonMsg("Effect/Direction12.img/effect/tuto/memory/" + str(0 + isFemale), 0)
sm.sendDelay(2000)
sm.showBalloonMsg("Effect/Direction12.img/effect/story/BalloonMsg0/1", 2000)
sm.sendDelay(2000)

sm.sendNext("What was that?! Like... someone else's memory! My chest... I can't catch my breath...")
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/story/BalloonMsg0/0", 2000, CLAUDINE_CAGED)

sm.spawnNpc(GELIMER_PRESENT, -824, 43)
sm.spawnNpc(BERYL_FORCEMOVE, -824, 43)
sm.moveCamera(False, 1000, -1000, 43)
sm.moveNpcByTemplateId(GELIMER_PRESENT, False, 800, 200)
sm.moveNpcByTemplateId(BERYL_FORCEMOVE, False, 800, 180)
sm.moveCamera(False, 500, 1000, 43)
sm.sendDelay(5000)

sm.setSpeakerID(GELIMER_PRESENT)
sm.sendNext("What are you doing?! Capture them! Capture them all!")
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg1/2", 2000, BRIGHTON_FIGHT)
sm.sendDelay(2000)
sm.setSpeakerID(BRIGHTON_FIGHT)
sm.sendNext("Belle! Get out of here!")
sm.setSpeakerID(BELLE_FIGHT)
sm.sendSay("What about Claudine?")
sm.setSpeakerID(BRIGHTON_FIGHT)
sm.sendSay("She'll be okay! We need to get back-up!")
sm.showEffectOnPosition("Effect/Direction12.img/effect/tuto/smog", 10000, 550, 0)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg2/14", 2000, 0, -300, CLAUDINE_CAGED)
sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 2000, GELIMER_PRESENT)
sm.sendDelay(2000)
sm.removeNpc(BRIGHTON_FIGHT)
sm.removeNpc(BELLE_FIGHT)
sm.setSpeakerID(GELIMER_PRESENT)
sm.sendNext("Don't let them get away!")
sm.sendSay("Xenon! Watch this one! Beryl, you and I will chase down the rest of these rats!")

sm.moveNpcByTemplateId(GELIMER_PRESENT, False, 800, 100)
sm.moveNpcByTemplateId(BERYL_FORCEMOVE, False, 800, 80)
sm.setPlayerAsSpeaker()
sm.sendNext("What happened earlier?")
sm.warpInstanceIn(931050950)

sm.lockInGameUI(False)

sm.removeNpc(GELIMER_PRESENT)
sm.removeNpc(BERYL_FORCEMOVE)
sm.removeNpc(CLAUDINE_CAGED)
