# Created by MechAviv
# Quest ID :: 34919
# Not coded yet

sm.setSpeakerID(3001509)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face4#On this planet, the caravan eats #o2400351# from #r#m402000611##k!\r\n                            You'd really be helping if you could get us #i4036345# #b#t4036345##k x20!"):
    sm.setSpeakerID(3001509)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face3#Ohhh, our staple food tastes so very very baaad.\r\nBut singing while eating makes me so very very glaaad!")


    # Unhandled Message [47] Packet: 2F 01 00 00 00 40 9C 00 00 00 00 00 00 28 00 00 00 00 00 00 80 05 BB 46 E6 17 02 0C 00 75 73 65 72 5F 6C 76 75 70 3D 31 32
    sm.startQuest(34919)
    # [START_NAVIGATION] [E3 0A F6 17 00 00 00 00 00 00 ]