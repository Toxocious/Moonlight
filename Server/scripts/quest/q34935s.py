# Created by MechAviv
# Quest ID :: 34935
# Not coded yet

sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face6#I'm... I'm okay. Let's get out of here. We've got to find the last piece of the crystal.")


# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 16 02 00 00 FF 00 00 00 00
sm.setSpeakerID(3001510)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face2#Right. Let's get aboveground everyone."):
    sm.setSpeakerID(3001510)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face4#We're lucky. I've got a strong lock on the signal for the last piece.")


    sm.startQuest(34935)
    sm.warp(402090012, 0)
