# Created by MechAviv
# Quest ID :: 5671
# Not coded yet

sm.completeQuest(5671)
sm.completeQuest(5758)
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.showNpcSpecialActionByTemplateId(9270083, "summon", 0)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Burke! Are you okay?")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("It's nothing... But one of them took the key to the shuttle...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("He's in the control room... I think he's going to shut everything down.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I'll stop them, Burke. You just wait here.")


sm.sendDelay(1000)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("...Jett...")


sm.sendDelay(1000)


sm.chatScript("Follow the arrows to the Key Keeper's Room.")
sm.startQuest(5672)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
