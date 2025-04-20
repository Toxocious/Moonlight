# Created by MechAviv
# Quest ID :: 25562
# Fostering the Dark

sm.setSpeakerID(0)
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Dark magic is so much easier than light...")


sm.setSpeakerID(0)
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("But I do not fully understand it. With every minor touch, I feel the lust for destruction well up within me. It would be foolish to use this power without more understanding.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("It's time I left this forest and found some answers.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("The world has changed in the last few centuries. Thankfully, I have a map I can check by pressing #b#e[W] (basic key setting) or [N] (secondary key settings)#n#k.")

sm.startQuest(25562)
sm.completeQuest(25562)
sm.giveExp(900)
sm.startQuest(25559)