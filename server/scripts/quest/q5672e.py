# Created by MechAviv
# Quest ID :: 5672
# Not coded yet

sm.completeQuest(5672)
sm.completeQuest(5758)
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.sendDelay(1000)


sm.setSpeakerID(9270085)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("I got it. Guess it's time to be going.")


sm.sendDelay(1000)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)

sm.warp(620100028, 0)
