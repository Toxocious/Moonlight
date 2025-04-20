# Created by MechAviv
# Map ID :: 940000030
# Hidden Street : Somewhere Near Pantheon

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(2)
sm.sendDelay(1200)


sm.forcedInput(0)
sm.showEffect("Effect/Direction9.img/effect/story/BalloonMsg0/4", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(0)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("No good...the aura is gone. They have escaped my wrath...for now.")


sm.setSpeakerID(0)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendSay("They must be plotting something. I need to let Cartalion know about this.")


sm.createQuestWithQRValue(25802, "1")
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
