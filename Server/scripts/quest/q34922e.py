# Created by MechAviv
# Quest ID :: 34922
# Not coded yet

# Inventory Operation with 1 operations.
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.zoomCamera(0, 2000, 0, 900, 150)


sm.teleportInField(756, 90)
sm.forcedFlip(False)
sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face0#Phew! Done with repairs! Guess I'll go talk to Ferret again.")


sm.showNpcSpecialActionByTemplateId(3001511, "summon", 0)
sm.moveNpcByTemplateId(3001511, False, 300, 100)
sm.sendDelay(1000)


sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face2#Isn't that... Mar?")


sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face2#What's he doing going outside the refuge?")


sm.zoomCamera(2000, 2000, 2000, 1200, 150)


sm.forcedMove(False, 300)
sm.sendDelay(2000)


sm.flipNpcByTemplateId(3001511, True)
sm.showNpcSpecialActionByTemplateId(3001511, "falldown", 0)
sm.sendDelay(3500)


sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face2#Mar, what are you doing? It's dangerous out there!")


# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 FB 00 00 00 FF 00 00 00 00
sm.setSpeakerID(3001511)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face2#Hehe...")


sm.flipNpcByTemplateId(3001511, False)
sm.moveNpcByTemplateId(3001511, False, 150, 100)
sm.sendDelay(500)


sm.forcedMove(False, 300)
sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face4#Where is he going...? Guess I'll follow for now.")


sm.zoomCamera(500, 2000, 500, 1300, 150)


sm.sendDelay(1000)


sm.completeQuest(34922)
# Unhandled Stat Changed [EXP] Packet: 00 00 00 00 01 00 00 00 00 00 51 06 00 00 00 00 00 00 FF 00 00 00 00
sm.giveExp(932)
# Update Quest Record EX | Quest ID: [34922] | Data: exp=1
# Update Quest Record EX | Quest ID: [34995] | Data: 00=h1;01=h1;10=h0;02=h1;11=h0;12=h0;04=h1;13=h0;05=h0;14=h0;23=h0;06=h0;15=h0;24=h0;07=h0;16=h0;17=h0;09=h0
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.setStandAloneMode(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 07 00 00 00 40 9C 00 00 00 00 00 00 28 00 00 00 00 00 00 80 05 BB 46 E6 17 02 0C 00 75 73 65 72 5F 6C 76 75 70 3D 31 36 B8 58 08 00 00 00 00 00 23 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0D 00 6D 6F 62 5F 6B 69 6C 6C 3D 34 36 32 39 58 68 08 00 00 00 00 00 27 02 00 00 00 00 00 80 05 BB 46 E6 17 02 0D 00 6D 6F 62 5F 6B 69 6C 6C 3D 34 36 32 39 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 70 5E 09 00 00 00 00 00 66 02 00 00 00 00 00 80 05 BB 46 E6 17 02 13 00 63 6F 6D 62 6F 6B 69 6C 6C 5F 69 6E 63 72 65 73 65 3D 37 C8 E4 0C 00 00 00 00 00 4D 03 00 00 00 00 00 80 05 BB 46 E6 17 02 1D 00 70 69 63 6B 75 70 5F 6D 6F 62 5F 72 65 77 61 72 64 5F 6D 65 73 6F 3D 35 36 37 31 37 30 80 F0 0C 00 00 00 00 00 50 03 00 00 00 00 00 80 05 BB 46 E6 17 02 1D 00 70 69 63 6B 75 70 5F 6D 6F 62 5F 72 65 77 61 72 64 5F 6D 65 73 6F 3D 35 36 37 31 37 30
#sm.warp(402000613, 0) not sure