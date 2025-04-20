# Created by MechAviv
# ID :: [910142050]
# Hidden Street : Harmony

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
OBJECT_1 = sm.sendNpcController(1032209, -15, -30)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Did you come to Harmony to train in the Dark arts? I can't blame you. We have plenty of books on the matter.")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I wrestle for control of my own mind and soul at all times! I fear this evil energy will swallow me whole before I am able to control it. I must find a wielder of light that is as powerful as I am...")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I may not have a body anymore, but I'm much stronger than last time you saw me. Don't make me whip out my Light-fu!")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Vieren... you have truly embraced the power of Light. You are not the same man I remember. Do you truly believe the power of light can suppress the darkness? I suppose if this does not work, we will both burn to cinder and ash.")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("All right. Let's get this over with!")


sm.faceOff(21066)
sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/1", 2100, 0, 0, -5, -2, False, 0)
sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/2", 2100, 0, 0, -6, -2, False, 0)
sm.sendDelay(1800)


OBJECT_2 = sm.sendNpcController(1032217, -330, -30)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.setSpeakerID(1032217)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Luminous! Stop!")


sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(1032217)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("...Lania?")


sm.setSpeakerID(1032217)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I've been waiting for you, Luminous. Constantly. Since before I forgot...")


sm.setSpeakerID(1032217)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Lania? Lania!")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("...Luminous?")


sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg1/3", 0, 0, -180, -2, -2, False, 0)
sm.sendDelay(2100)


sm.showFieldEffect("demonSlayer/whiteOut", 0)
sm.sendDelay(4000)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.warp(910142051, 0)
