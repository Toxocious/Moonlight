# Created by MechAviv
# Quest ID :: 34931
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
sm.sendNext("#face2#We lost the signal again. The sandstorm got even stronger than before.")


sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face0#We'll just have to do a little more hunting.")


sm.setSpeakerID(3001510)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face0#That's our best shot, yeah.")


sm.setSpeakerID(3001510)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face0#How about #b50#k more #b#o2400357##k monsters?"):
    sm.setSpeakerID(3001510)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face0#Do what you can, and I'll keep searching for that signal.")


    sm.startQuest(34931)