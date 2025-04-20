# Created by MechAviv
# Quest ID :: 25533
# The Guardian Returns

sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("The first step is to find out what has happened to this world while I was gone. And what of those who fought beside me? What of Aurora and it's masters? I must find the answers.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Perhaps the nearby village will have answers for me.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("The world has changed in my years of slumber. Thankfully, I have a map I can check by pressing #b#e[W] (basic key setting) or [N] (secondary key settings)#n#k.")

sm.startQuest(25533)
sm.completeQuest(25533)
sm.giveExp(900)
sm.startQuest(25559)
sm.warp(101000000, 15)
