# Created by MechAviv
# Map ID :: 940011040
# Eastern Region of Pantheon : East Sanctum

# [SET_DRESS_CHANGED] [00 00 ]
sm.giveAndEquip(1352601)
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.forcedInput(4)
OBJECT_1 = sm.sendNpcController(3000141, -150, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000115, 200, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(3000111, 300, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.sendDelay(1200)
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")


sm.showEffect("Effect/BasicEff.img/Kaiser_Transform4_S", 0, 0, 0, 0, OBJECT_1, False, 0)
sm.setSpeakerID(3000115)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("W-what is this madness?!")


sm.setSpeakerID(3000111)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("How could a mere child have that kind of power?!")
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")


sm.setSpeakerID(3000115)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("He seems to be unconscious. We are lucky.")
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")


sm.setSpeakerID(3000111)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("They came out of nowhere. We must eliminate them before more come.")
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")


sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg0/1", 1200, 0, -100, -2, -2, False, 0)
sm.sendDelay(900)
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")


sm.forcedInput(0)
sm.setSpeakerID(3000115)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("He's waking up!")


sm.sendDelay(150)


sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")
sm.forcedAction(497, 0)
sm.showEffect("Skill/6512.img/skill/65121002/effect", 0, 0, 0, -2, -2, False, 0)
sm.playSound("Angelicburster/65121002", 100)
sm.sendDelay(900)


sm.showFieldEffect("demonSlayer/whiteOut", 0)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "die1", 0)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "die1", 0)
sm.sendDelay(1200)


sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.sendDelay(720)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.sendNpcController(OBJECT_1, False)
sm.warp(940011050, 0)
