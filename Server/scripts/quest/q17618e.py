# Created by MechAviv
# Quest ID :: 17618
# [Commerci Republic] The Lady in Robes

sm.setIntroBoxChat(9390204)
sm.sendNext("I can't believe it. The last of my money, down the drain. I don't suppose they'll give me a refund.")


sm.setSpeakerID(9390204)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Now hand over those goods!")


sm.setSpeakerID(9390204)
sm.removeEscapeButton()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Sigh. Why are you getting in my way? ")


sm.setSpeakerID(9390204)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("I have my reasons. Besides, stealing is bad!")


sm.setSpeakerID(9390204)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("So what'll it be? Are you going to hand over the goods, or will we have to do this the hard way?")


sm.setSpeakerID(9390204)
sm.removeEscapeButton()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Hmph. Stealing is bad, but bullying is okay? Fine, take your stupid trade goods. I'm out of here.")


# [NPC_VIEW_OR_HIDE] [E0 B3 53 00 00 00 ]
sm.setSpeakerID(9390204)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("That was easy. Sorta. Better get these back to town.")


sm.setSpeakerID(9390204)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Who was it that lost these items? Was his name... Tepes?")


# Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
sm.completeQuest(17618)
sm.startQuest(17619)
# Unhandled Stat Changed [EXP] Packet: 00 00 00 00 01 00 00 00 00 00 9A 3A E0 01 00 00 00 00 FF 00 00 00 00
sm.giveExp(5530255)
# [GUILD_RESULT] [74 F9 DA 01 00 8B 02 00 00 03 00 00 00 BA 00 00 00 ]
# [GUILD_RESULT] [6D F9 DA 01 00 1E 4F 85 00 8B 02 00 00 16 00 00 00 B2 01 00 00 C0 CE 20 27 38 ED D4 01 ]
# Update Quest Record EX | Quest ID: [18418] | Data: B=1344
# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 EB 0A 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 E7 0A 00 00 FF 00 00 00 00
# [SUMMONED_REMOVED] [1E 4F 85 00 2E 37 26 01 02 ]
sm.warpInstanceOut(865000000, 10)
