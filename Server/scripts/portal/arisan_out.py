# Created by MechAviv
# Map ID :: 749080900
# Alishan : Leshan

sm.setSpeakerID(0)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.setSpeakerType(8)
if sm.sendAskAccept("Hmm... I wonder where this goes? Only one way to find out!"):
    sm.warp(100000000)