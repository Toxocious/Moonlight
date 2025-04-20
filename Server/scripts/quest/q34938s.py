# Created by MechAviv
# Quest ID :: 34938
# Not coded yet

sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face5#All right, I'll be the bait. You all defend, okay?")


sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face8#We have to stay strong for as long as it takes Ferret to get that thing going.")


sm.setSpeakerID(3001500)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face5#It'll take everything we've got, but we can do it. Let's go."):
    # [CLEAR_ANNOUNCED_QUEST] []
    sm.systemMessage("Defend the refuge from the Specter forces.")
    # Unhandled Message [47] Packet: 2F 01 00 00 00 59 03 04 00 00 00 00 00 07 01 00 00 01 02 70 59 25 74 8B D6 D4 01 14 00 71 75 65 73 74 5F 73 74 61 74 65 5F 63 68 61 6E 67 65 3D 31
    sm.warp(940205100, 0)
