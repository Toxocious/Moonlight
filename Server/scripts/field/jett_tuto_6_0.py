# Created by MechAviv
# Map ID :: 620100026
# Spaceship : In Front of the Shuttle
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)

sm.giveSkill(228, 1, 1)
OBJECT_1 = sm.sendNpcController(9270083, 430, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
for i in range(3):
    sm.spawnMob(9420564, -450, -120, False)
sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("The guardsmen are already here! Does this mean our crew is...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("No... They were good people! I'm innocent! WHY CAN'T YOU SEE THAT?!")


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg1/3", 2000, 0, -80, -2, -2, False, 0)
sm.sendDelay(500)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/attack_tuto", 3000, 0, -200, -2, -2, False, 0)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.chatScript("Eliminate all Guards.")
sm.startQuestNoCheck(5671)