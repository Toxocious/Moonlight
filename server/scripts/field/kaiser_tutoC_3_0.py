# Created by MechAviv
# Map ID :: 940001230
# Eastern Region of Pantheon : East Sanctum

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.hideUser(True)
OBJECT_1 = sm.sendNpcController(3000142, -100, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000139, -150, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(3000114, 70, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(3000111, 130, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
OBJECT_5 = sm.sendNpcController(3000115, 250, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
sm.setSpeakerID(3000114)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Wh-What is this...")


sm.setSpeakerID(3000111)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("That kid transformed into this?")


sm.setSpeakerID(3000114)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Looks dangerous...")


sm.showEffect("Effect/Direction9.img/effect/tuto/BalloonMsg0/2", 1200, 0, -200, 0, OBJECT_1, False, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "DKgigaSlasher", 0)
sm.showEffect("Skill/6112.img/skill/61121201/effect", 0, 0, 0, -2, -2, False, 0)
sm.playSound("Kaiser/61121100", 100)
sm.sendDelay(300)


sm.showNpcSpecialActionByObjectId(OBJECT_3, "die1", 0)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "die1", 0)
sm.sendDelay(1500)


sm.sendNpcController(OBJECT_3, False)
sm.sendNpcController(OBJECT_4, False)
sm.setSpeakerID(3000115)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("W-what is this madness?!")


sm.showFieldEffect("demonSlayer/whiteOut", 0)
sm.sendDelay(1950)


sm.hideUser(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.sendNpcController(OBJECT_4, False)
sm.sendNpcController(OBJECT_5, False)
sm.warp(940001240, 0)
