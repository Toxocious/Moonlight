# Created by MechAviv
# Map ID :: 620100020
# Spaceship : Corridor
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)

OBJECT_1 = sm.sendNpcController(9201276, 40, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(9201277, -270, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(9201278, -170, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(9201279, -90, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
OBJECT_5 = sm.sendNpcController(9270083, 150, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
sm.showFieldEffect("newPirate/text4", 0)
sm.sendDelay(1900)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Good work, team. All of you together were able to bring in the whole group.")


sm.sendDelay(1000)


sm.setSpeakerID(9201276)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Whatever, Burke! You're nothing without that loser with the Core. That's the only reason you had a chance!")


sm.setSpeakerID(9201276)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Man, shut your face. I don't want to rough you up before we turn you in. Lock them up and keep an eye on them!")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Roger that, chief.")


sm.sendDelay(1200)


sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.sendNpcController(OBJECT_4, False)
sm.sendNpcController(OBJECT_5, False)
sm.warp(620100021, 0)
