# Created by MechAviv
# Quest ID :: 34932
# Not coded yet

sm.setSpeakerID(3001510)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face0#At least we're still getting a signal...")


sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face0#Lead the way.")


sm.setSpeakerID(3001510)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face0#Okay. We can just follow it until we find them."):
    # Update Quest Record EX | Quest ID: [34995] | Data: 00=h1;10=h0;01=h0;11=h0;02=h0;12=h0;13=h0;04=h0;23=h0;14=h0;05=h0;24=h0;15=h0;06=h0;16=h0;07=h0;17=h0;09=h0
    # Update Quest Record EX | Quest ID: [34995] | Data: 00=h1;10=h0;01=h0;11=h0;02=h0;12=h1;13=h0;04=h0;23=h0;14=h0;05=h0;24=h0;15=h0;06=h0;16=h0;07=h0;17=h0;09=h0
    sm.startQuest(34932)
    # [START_NAVIGATION] [F7 0A F6 17 00 00 00 00 00 00 ]
    # [SET_PARTNER] [01 A6 CC 2D 00 5D BD C4 04 00 ]
