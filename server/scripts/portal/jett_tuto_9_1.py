# Created by MechAviv
# Map ID :: 620100029
# Spaceship : In Front of the Shuttle

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.startQuest(53246)
sm.completeQuest(53246)
sm.sendDelay(1000)


sm.forcedAction(379, 0)
sm.sendDelay(2000)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("We're en route. Can you really bring us down over Maple World?")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Kshhhhh... Of course... Our power... is great... Kshhhhh... this...")


sm.sendDelay(2000)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# [FORCED_STAT_RESET] []
sm.warp(620100040, 0)
