# Created by MechAviv
# Quest ID :: 34917
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
sm.sendNext("#face3#Was there something else? Or... is something wrong?")


sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face0#I'll stay here at the refuge and help you. Like you said, survival first, right?")


sm.setSpeakerID(3001508)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face2#We haven't even paid you back for saving Mar, but we really could use the help...")


sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face4#Don't worry. Helping you and finding a way out of here benefits all of us.")


sm.setSpeakerID(3001508)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face2#So, we're helping each other out. Okay. In that case, we could really use medical supplies to treat the wounded.")


sm.setSpeakerID(3001508)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face2#Bring #b20#k #i4036344# #b#t4036344##k items from #o2400350# in #r#m402000610##k."):
    sm.setSpeakerID(3001508)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face2#I'm sorry again for being so mean to you before. Can't be too careful. See you soon!")


    sm.startQuest(34917)
    # [START_NAVIGATION] [E2 0A F6 17 00 00 00 00 00 00 ]