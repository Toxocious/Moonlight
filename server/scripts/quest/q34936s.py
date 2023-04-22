# Created by MechAviv
# Quest ID :: 34936
# Not coded yet

sm.setSpeakerID(3001509)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face2#Haha! Now it's time for my pyrotechnic magic! Yes!")


sm.setSpeakerID(3001512)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face0#This place looks familiar... I don't think I like it here.")


sm.setSpeakerID(3001513)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face0#Ferret, are we sure it's okay to use explosives here?")


sm.setSpeakerID(3001510)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face4#It's all right. We're a long way from that cave. It's very sturdy here.")


sm.setSpeakerID(3001510)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face1#Hm, the signal is getting weaker. Time to do your thing, Salvo! And hurry!")


sm.setSpeakerID(3001509)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face3#Yes! I get to blow stuff up!\r\nGet ready for some fun!")


sm.setSpeakerID(3001509)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face4#Let's get started, everyone!"):
    sm.setSpeakerID(3001509)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face3#Listen to the rhythm of your heaaart.\r\nHit the button, and blast stuff apaaart!")


    # Unhandled Message [47] Packet: 2F 02 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 D3 22 11 00 00 00 00 00 63 04 00 00 1B 02 C0 F9 12 4B 8B D6 D4 01 0D 00 66 69 65 6C 64 5F 65 6E 74 65 72 3D 31
    sm.warp(402090006, 0)
