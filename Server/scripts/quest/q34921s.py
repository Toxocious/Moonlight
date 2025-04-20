# Created by MechAviv
# Quest ID :: 34921
# Not coded yet

sm.setSpeakerID(3001510)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face0#We use #i4036346# #b#t4036346##k from #o2400352# at #r#m402000612##k for all kinds of refuge and armor repairs. Why don't you bring back #b20#k for me?"):
    sm.setSpeakerID(3001510)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face0#It's not perfect, but #t4036346# is the toughest material we've got out here. See what you can find.")


    sm.startQuest(34921)
    # [START_NAVIGATION] [E4 0A F6 17 00 00 00 00 00 00 ]