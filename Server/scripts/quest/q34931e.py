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
sm.sendNext("#face1#Good work! I'm getting the signal again. We need to move quickly. Follow me.")


sm.setSpeakerID(3001509)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face3#Oh, we didn't let the sandstorm get us down!\r\nNow our trouble's behind us, and we're searchin' around!")


# Update Quest Record EX | Quest ID: [34995] | Data: 00=h1;10=h0;01=h0;11=h0;02=h0;12=h0;13=h0;04=h0;23=h0;14=h0;05=h0;24=h0;15=h0;06=h0;16=h0;07=h0;17=h0;09=h0
sm.completeQuest(34931)
# Unhandled Stat Changed [EXP] Packet: 00 00 00 00 01 00 00 00 00 00 EB 21 00 00 00 00 00 00 FF 00 00 00 00
sm.giveExp(7360)
# Update Quest Record EX | Quest ID: [34931] | Data: exp=1
# Update Quest Record EX | Quest ID: [34995] | Data: 00=h1;10=h0;01=h0;11=h1;02=h0;12=h0;13=h0;04=h0;23=h0;14=h0;05=h0;24=h0;15=h0;06=h0;16=h0;07=h0;17=h0;09=h0
# Unhandled Message [47] Packet: 2F 0A 00 00 00 40 9C 00 00 00 00 00 00 28 00 00 00 00 00 00 80 05 BB 46 E6 17 02 0C 00 75 73 65 72 5F 6C 76 75 70 3D 32 36 B8 58 08 00 00 00 00 00 23 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0D 00 6D 6F 62 5F 6B 69 6C 6C 3D 34 39 35 38 58 68 08 00 00 00 00 00 27 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0D 00 6D 6F 62 5F 6B 69 6C 6C 3D 34 39 35 38 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0B 00 6D 6F 62 5F 6B 69 6C 6C 3D 31 38 70 5E 09 00 00 00 00 00 66 02 00 00 00 00 00 80 05 BB 46 E6 17 02 14 00 63 6F 6D 62 6F 6B 69 6C 6C 5F 69 6E 63 72 65 73 65 3D 32 32 E0 75 09 00 00 00 00 00 6C 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0D 00 6D 75 6C 74 69 6B 69 6C 6C 3D 34 32 34 98 81 09 00 00 00 00 00 6F 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0D 00 6D 75 6C 74 69 6B 69 6C 6C 3D 34 32 34 50 8D 09 00 00 00 00 00 72 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0A 00 6D 6F 62 5F 6B 69 6C 6C 3D 38 08 99 09 00 00 00 00 00 75 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0A 00 6D 6F 62 5F 6B 69 6C 6C 3D 38 C4 22 11 00 00 00 00 00 63 04 00 00 0C 02 A0 18 36 98 8A D6 D4 01 0D 00 66 69 65 6C 64 5F 65 6E 74 65 72 3D 31
sm.warp(402090005, 0)
