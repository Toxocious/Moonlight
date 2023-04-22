# Created by MechAviv
# Map ID :: 940001050
# Hidden Street : East Pantheon

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
OBJECT_1 = sm.sendNpcController(3000107, -2000, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("There are Specters here as well?")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("The situation might be more serious than I expected.")


sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("This isn't good. Go back and activate the shield as soon as possible.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("This is precisely when you need the most help. Even if you are Kaiser, you can't make it by yourself...")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Cartalion, you are a knight of Nova. Your first duty is always to the people of Nova. You must protect them, not me. As Kaiser, I fight for others, not the other way around.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("As you wish. Good luck out there.")


sm.sendDelay(1000)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.warp(940001100, 0)
