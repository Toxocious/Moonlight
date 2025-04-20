FREUD = 2159357

sm.lockInGameUI(True)
sm.curNodeEventEnd(True)

sm.spawnNpc(FREUD, 300, -80)
sm.showNpcSpecialActionByTemplateId(FREUD, "summon", 0)

sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/6", 0, 0, -160, -2, -2, False, 0)
sm.sendDelay(1200)

sm.moveCamera(False, 300, 0, -500)

sm.showFieldEffect("lightning/screenMsg/2")
sm.playExclSoundWithDownBGM("Voice.img/DarkMage/0", 100)
sm.sendDelay(4000)

sm.moveCamera(False, 300, 300, -100)

sm.showEffect("Effect/Direction5.img/effect/mercedesInIce/merBalloon/0", 0, 0, -90, 0, sm.getNpcObjectIdByTemplateId(FREUD), False, 0)
sm.sendDelay(2100)

sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("Freud! Mercedes!")
sm.sendDelay(300)

sm.forcedInput(2)
sm.moveCamera(True, 180, 0, 0)