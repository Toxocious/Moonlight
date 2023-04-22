# Created by MechAviv
# Quest ID :: 34926
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
sm.sendNext("#face0#Whenever we explore, it's inevitable that we will be separated at some point. That's why we need a special signal device.")


sm.setSpeakerID(3001510)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face1#This one has a unique signal we can use to find each other.")


sm.setSpeakerID(3001510)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face0#Since you're new, we'll have to make a new device for you. And mine needs to be repaired.")


sm.setSpeakerID(3001510)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face4#We'll need a lot of supplies. Try finding #b20#k #i4036348# #b#t4036348##k items from #o2400354# monsters in #r#m402000616##k."):
    sm.setSpeakerID(3001510)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face0#I'll stay here and do some equipment maintenance to prep for the expedition.")


    sm.startQuest(34926)
    # [START_NAVIGATION] [E8 0A F6 17 00 00 00 00 00 00 ]
