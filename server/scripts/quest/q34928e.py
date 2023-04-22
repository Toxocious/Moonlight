# Created by MechAviv
# Quest ID :: 34928
# Not coded yet

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.removeAdditionalEffect()
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
sm.sendNext("#face0#(You brush away the sand to reveal something glittering in the sun.)")


sm.blind(1, 150, 0, 0, 0, 500, 0)
sm.playSound("Sound/SoundEff.img/PinkBean/expectation", 100)
sm.OnOffLayer_On(300, "d0", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/7/0", 4, 1, -1, 0)
sm.sendDelay(1000)


sm.blind(0, 0, 0, 0, 0, 500, 0)
sm.OnOffLayer_Off(500, "d0", 0)
sm.sendDelay(500)


sm.setSpeakerID(3001510)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face2#Hm, it's only a fragment. It might not be usable.")


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
sm.sendSay("#face0#No, the power source was made up of three different parts. This must be the first piece we need. Let's take this back to the refuge.")


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
sm.sendSay("#face0#(If you're right, the crystal will work once you find the other two fragments. There may yet be hope.)")


sm.completeQuest(34928)
# Unhandled Stat Changed [EXP] Packet: 00 00 00 00 01 00 00 00 00 00 E9 14 00 00 00 00 00 00 FF 00 00 00 00
sm.giveExp(5285)
# Update Quest Record EX | Quest ID: [34928] | Data: exp=1;e1=1
# Update Quest Record EX | Quest ID: [34995] | Data: 00=h1;10=h0;01=h1;11=h0;02=h1;12=h0;13=h0;04=h0;23=h0;14=h0;05=h0;24=h0;15=h0;06=h0;16=h0;07=h0;17=h0;09=h0
sm.setMapTaggedObjectVisible("core0", False, 0, 0)
sm.setMapTaggedObjectVisible("core1", False, 0, 0)
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 02 00 00 00 40 9C 00 00 00 00 00 00 28 00 00 00 00 00 00 80 05 BB 46 E6 17 02 0C 00 75 73 65 72 5F 6C 76 75 70 3D 32 33 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.warp(402000600, 3)
