# Created by MechAviv
# ID :: [910142041]
# Ellinia : Magic Library

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
OBJECT_1 = sm.sendNpcController(1032208, -106, 182)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(1032208)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("You got what you were looking for. Now please go!")


sm.setSpeakerID(1032208)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("This is rich. You give me an incomplete scroll, then order me to leave?")


sm.setSpeakerID(1032208)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Do you even know who I am, child?!")


sm.setSpeakerID(1032208)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("N-no! I don't want any trouble! I just have to clean the library before--")


sm.setSpeakerID(1032208)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Enough. BEGONE!")


sm.faceOff(21066)
sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/1", 2100, 0, 0, -5, -2, False, 0)
sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/2", 2100, 0, 0, -6, -2, False, 0)
sm.sendDelay(300)


sm.showEffect("Effect/Direction8.img/effect/story/roroEffect", 0, 0, 0, 0, OBJECT_1, False, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "hit", 0)
sm.sendDelay(660)


sm.sendNpcController(OBJECT_1, False)
sm.sendDelay(1140)


sm.faceOff(0)
sm.setSpeakerID(1032208)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Peace and quiet, at long last.")


sm.setSpeakerID(1032208)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Ah... W-what did I... just do? ")

sm.startQuest(25569)
sm.completeQuest(25569)
sm.giveExp(758)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
