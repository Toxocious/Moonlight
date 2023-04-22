sm.lockUI()
FANZY = 1500010

sm.removeEscapeButton()

sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("#bBleh! I almost drowned!#k")

sm.setSpeakerID(FANZY)
sm.sendSay("There must be some kind of enchantment to keep people from swimming across.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("#bYou could have told me that in advance!#k")

sm.setSpeakerID(FANZY)
sm.sendSay("I'm not omniscient, and you make a good test subject. We'll have to find another way.")

sm.unlockUI()
sm.startQuest(32102)
sm.completeQuest(32102)

sm.warp(101070000, 0)