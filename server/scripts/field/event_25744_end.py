# Created by MechAviv
# Map ID :: 940000090
# West Pantheon : Near West Sanctum

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.setSpeakerID(0)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("The enemies don't know anything about the Relic. Did I beat the sense out of them?")


sm.setSpeakerID(0)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendSay("And I don't feel any evil aura. is this it?")


sm.forcedInput(1)
sm.sendDelay(300)


sm.forcedInput(2)
sm.sendDelay(600)


sm.forcedInput(1)
sm.sendDelay(300)


sm.forcedInput(0)
sm.sendDelay(600)


sm.setSpeakerID(0)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("I guess I should think about this harder. Nah, I'll just let Cartalion know about this.")


sm.setSpeakerID(0)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendSay("He said he is investigating something about Nefarious Priests. I'll see where he is with that.")


sm.createQuestWithQRValue(25805, "1")
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
