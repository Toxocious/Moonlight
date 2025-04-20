# Created by MechAviv
# Quest ID :: 34918
# Not coded yet

sm.setSpeakerID(3001508)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face0#That's perfect! And now, do you think you could help gather food?"):
    sm.setSpeakerID(3001508)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face0#Salvo organizes food procurement. If you find him, I'm sure he'll be able to tell you what we need.")


    sm.startQuest(34918)
    # [START_NAVIGATION] [D8 0A F6 17 01 00 00 00 07 00 33 30 30 31 34 30 31 ]