# Created by MechAviv
# Map ID :: 940001100
# Heliseum : Heliseum Outskirts
OBJECT_1 = sm.getIntroNpcObjectID(3000131)

sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Heliseum? Darmoor took it. Plain and simple.")


sm.showNpcSpecialActionByObjectId(OBJECT_1, "fake", 0)
sm.showEffect("Effect/Direction9.img/effect/story/BalloonMsg1/1", 0, 0, -110, -2, -2, False, 0)
sm.forcedInput(2)
sm.curNodeEventEnd(True)
