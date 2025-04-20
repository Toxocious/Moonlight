# Created by MechAviv
# Quest ID :: 17618
# [Commerci Republic] The Lady in Robes

sm.setSpeakerID(0)
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
if sm.sendAskYesNo("#b(Okay, so there were a lot of goods that were stolen, and the thief was working alone... Chances are, he couldn't have gotten far. I can catch him!)#k"):
    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("Okay, the opposite direction of Berry... To the east I go!")


    sm.startQuest(17618)
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    # [SUMMONED_REMOVED] [1E 4F 85 00 2B 37 26 01 02 ]
    sm.warpInstanceIn(865020001, 1)
else:
    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSayOkay("Too much pressure! I'm just not ready yet!")