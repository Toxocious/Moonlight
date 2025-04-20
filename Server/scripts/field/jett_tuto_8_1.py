# Created by MechAviv
# Map ID :: 620100028
# Spaceship : In Front of the Shuttle

sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("You found the #bMaster Key#k!")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("The ship's all prepped! Let's turn and burn!")


sm.sendDelay(1000)


sm.setStandAloneMode(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)

sm.warp(620100029, 0)
