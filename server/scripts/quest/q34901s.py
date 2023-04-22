# Created by MechAviv
# Quest ID :: 34901
# Creeping Abyss

from net.swordie.ms.enums import UIType

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
sm.sendNext("#face0#(I may have lost my memories, but I've clearly got the hands of a warrior. I must already be trained for combat.)")


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
sm.sendSay("#face5#(I've got to prepare myself for battle. Based on my current state, I've got some options.)")


sm.removeSkill(150010241)
sm.setJob(15500)
# Unhandled Stat Changed [STR] Packet: 00 00 40 00 00 00 00 00 00 00 23 00 FF 00 00 00 00
# Unhandled Stat Changed [DEX] Packet: 00 00 80 00 00 00 00 00 00 00 04 00 FF 00 00 00 00
# Unhandled Stat Changed [AP] Packet: 00 00 00 40 00 00 00 00 00 00 17 00 FF 00 00 00 00
# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 C2 00 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 C2 00 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
sm.addSP(2, True)
# Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
sm.giveSkill(155001100, 1, 20)
sm.giveSkill(155001102, 1, 20)
sm.giveSkill(155001103, 1, 10)
# [INVENTORY_GROW] [01 1C ]
# [INVENTORY_GROW] [02 1C ]
# [INVENTORY_GROW] [03 1C ]
# [INVENTORY_GROW] [04 1C ]
# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
sm.startQuest(34901)
sm.completeQuest(34901)
sm.giveItem(1143098)
sm.giveAndEquip(1353600)
sm.systemMessage("You've obtained the <Creeping Abyss> medal.")
sm.chatScript("You've obtained the <Creeping Abyss> medal.")
sm.completeQuest(34906)
sm.openUI(UIType.UI_STAT)
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
sm.sendSay("#face0#(Whatever happened to me must have weakened my overall stats, but I could probably strengthen myself by leveling up.)")


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
sm.sendSay("#face0#I'll have to do the best I can with what I've got.\r\n#bPress the default hot key (S) and select-type (C) to distribute AP.#k")


sm.openUIWithOption(UIType.UI_SKILL, 155000007)
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
sm.sendSay("#face0#My skills are looking good. I can distribute more SP if I need to, but I don't really have a lot yet.")


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
sm.sendSay("#face0#I should assign my important skills to Hotkeys. And if I need to open the skill window again, I can just press the default hotkey (K).")


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
sm.sendSay("#face0#I should make it a point to remember that I can set spell bullets to auto mode by right-clicking the skill icon.")


sm.openUIWithOption(UIType.UI_ITEM, 2)
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
sm.sendSay("#face0#There are bound to be some tough fights ahead, so I should keep some potions at the ready in the shortcut window.)")


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
sm.sendSay("#face0#Once I'm ready, I can talk to Zippy and head to town. This place seems pretty rough, so I hope I get there in one piece.")

sm.setQuestEx(51236, "StageKey", "0")
sm.startQuest(34915)
sm.warp(402090011, 0)
# Update Quest Record EX | Quest ID: [51236] | Data: StageKey=0