# 410000002
MOONBEAM = 3002100

sm.setSpeakerID(MOONBEAM)
sm.sendNext("The Fox God gave you his blessings, so you probably have a new skill! Check your skill window.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Fox Trot... Spirit Bond... I've never seen these skills before.")

sm.setSpeakerID(MOONBEAM)
sm.sendSay("They'll be a big help for a slow, weak thing like you. Here, let's talk some more so I can show you how they work!")

sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(916)


