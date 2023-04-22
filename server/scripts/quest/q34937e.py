# Created by MechAviv
# Quest ID :: 34937
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
sm.sendNext("#face0#The trick will be to split up and move in two groups.")


sm.blind(1, 255, 0, 0, 0, 500, 0)
sm.sendDelay(500)


sm.sendDelay(300)


sm.OnOffLayer_On(300, "d0", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/8/0", 4, 1, -1, 0)
sm.OnOffLayer_On(300, "d1", 190, -150, -1, "Effect/Direction17.img/effect/ark/illust/8/1", 4, 1, -1, 0)
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
sm.sendNext("#face5#Salvo, Digs, Grit and I can set up a defense line here...")


sm.OnOffLayer_Off(300, "d1", 0)
sm.OnOffLayer_On(300, "d2", 190, -150, -1, "Effect/Direction17.img/effect/ark/illust/8/2", 4, 1, -1, 0)
sm.OnOffLayer_On(300, "d3", 170, -130, -1, "Effect/Direction17.img/effect/ark/illust/8/3", 4, 1, -1, 0)
sm.sendDelay(300)


sm.OnOffLayer_Move(2000, "d3", -210, 150)
sm.sendDelay(2000)


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
sm.sendNext("#face0#In the meantime, Ferret can take the power source to the refuge and get that started.")


sm.OnOffLayer_Off(300, "d0", 0)
sm.OnOffLayer_Off(300, "d2", 0)
sm.OnOffLayer_Off(300, "d3", 0)
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
sm.sendSay("#face4#Then I can come back for all of you, and we can leave here for good.")


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
sm.sendSay("#face1#That may just work, and the sandstorm between here and the refuge seems to have calmed.")


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
sm.sendSay("#face5#Our problem is the crystal's charge time. We have to hold the Specters off until it's ready.")


sm.setSpeakerID(3001509)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face4#Sounds like a job for Bomb Master Salvo!")


sm.setSpeakerID(3001509)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face3#I'll make those monsters all go blammo!")


sm.blind(1, 255, 0, 0, 0, 0, 0)
sm.sendDelay(1200)


sm.blind(0, 0, 0, 0, 0, 1000, 0)
sm.sendDelay(1400)


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
sm.sendNext("#face0#I'll start toward the refuge now.")


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
sm.sendSay("#face4#If I get it running, I'll send a signal, so be ready.")


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
sm.sendSay("#face1#Good luck, everyone!")


# Update Quest Record EX | Quest ID: [34995] | Data: 00=h1;10=h0;01=h0;11=h0;02=h0;12=h0;13=h0;04=h0;23=h1;14=h0;05=h0;24=h0;15=h0;06=h0;16=h0;07=h0;17=h0;09=h0
sm.completeQuest(34937)
# Unhandled Stat Changed [EXP] Packet: 00 00 00 00 01 00 00 00 00 00 F5 1D 00 00 00 00 00 00 FF 00 00 00 00
sm.giveExp(7360)
# Update Quest Record EX | Quest ID: [34937] | Data: exp=1
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
