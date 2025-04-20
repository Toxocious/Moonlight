# Created by MechAviv
# Quest ID :: 34928
# Not coded yet

sm.setSpeakerID(3001508)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face0#Oh, good. You're here. Ferret got a better signal, and it turns out the crystal we need is in pieces and scattered all over the place.")


sm.setSpeakerID(3001508)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face0#We should check the piece at the location closest to the refuge first, so we can get an idea of what we're dealing with.")


# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 A6 01 00 00 FF 00 00 00 00
sm.setSpeakerID(3001508)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face0#Ark, go with Ferret and check the first location."):
    sm.setSpeakerID(3001510)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face4#Yes! The signal's coming from #r#m402000615##k. Let's check it out.")


    sm.startQuest(34928)
    # [START_NAVIGATION] [E7 0A F6 17 00 00 00 00 00 00 ]