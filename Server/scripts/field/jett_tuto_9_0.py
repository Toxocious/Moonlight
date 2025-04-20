# Created by MechAviv
# Map ID :: 620100029
# Spaceship : In Front of the Shuttle

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.setStandAloneMode(True)
sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(9270083, 2415, -134)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendSessionValue("Ade", OBJECT_1)
sm.forcedInput(1)
sm.sendDelay(1000)


sm.forcedInput(0)
sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg2/2", 0, 0, -100, 0, OBJECT_1, False, 0)
sm.sendDelay(2000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg2/1", 0, 0, -100, 0, OBJECT_1, False, 0)
sm.sendDelay(1000)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("#p9270083#. You follow me outta here and then you disappear. They're after me, not you. ")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("What are you talking about? We're... we're family. I won't let you face this on your own.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Look, just get on the shuttle!")


sm.sendDelay(1000)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Burke... Thank you...")


sm.sendDelay(2000)


sm.chatScript("Follow the arrows to the portal.")
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)