# Created by MechAviv
# Quest ID :: 25585
# Harmony

sm.setSpeakerID(0)
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Yes, the energies are resonating. The path to Harmony is about to open.")


sm.setSpeakerID(0)
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("With the power of Aurora, I will have absolute control over the Dark. I must not let it overcome me.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Now all I must do to reach Harmony is use the Vampiric Lantern...")


sm.startQuest(25585)