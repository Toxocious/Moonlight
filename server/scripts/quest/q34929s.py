# Created by MechAviv
# Quest ID :: 34929
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
sm.sendNext("#face0#(The caravaners look hopeful but anxious as you show them the crystal shard and explain that there are two more to find.)")


sm.setSpeakerID(3001508)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face3#We may have to go out pretty far from the refuge to recover the other two.")


sm.setSpeakerID(3001508)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face3#When you're ready to go, would you come to #r#m402000620##k?\r\nIt might be a long trip, so I want to say goodbye."):
    sm.setSpeakerID(3001508)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face0#And I'll gather the other caravaners, so we can give\r\nyou a good send-off for luck. See you there!")

    
    sm.startQuest(34929)
    # [START_NAVIGATION] [EC 0A F6 17 00 00 00 00 00 00 ]
