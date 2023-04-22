LANIA = 1032201

sm.forcedInput(2)
sm.sendDelay(30)

sm.forcedInput(0)
sm.removeEscapeButton()
sm.setSpeakerID(LANIA)
sm.sendNext("Don't take too long, okay? I know how you like to dilly-dally in town!")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("I will return as swiftly as I can, dear Lania.")
sm.sendSay("Lania, since I've started living here, for the first time in my life I'm--ARGH!")

sm.setSpeakerID(LANIA)
sm.sendSay("Luminous?")

sm.forcedAction(4, 6000)
sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/0", 5400, 0, 20, -2, -2, False, 0)
sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg1/1", 1400, 0, -120, -2, -2, False, 0)
sm.moveNpcByTemplateId(LANIA, True, 50, 100)
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene2")
sm.playExclSoundWithDownBGM("Bgm26.img/Flood", 100)
sm.sendDelay(500)

sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg1/2", 0, 0, -120, 0, sm.getNpcObjectIdByTemplateId(LANIA), False, 0)
sm.sendDelay(2000)

sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg1/3", 0, 0, -180, -2, -2, False, 0)
sm.sendDelay(2300)

sm.faceOff(21066)
sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/1", 0, 0, 0, -2, -2, False, 0)
sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/2", 0, 0, 0, -2, -2, False, 0)
sm.sendDelay(3000)

sm.removeNpc(LANIA)
sm.warp(910141060, 0)