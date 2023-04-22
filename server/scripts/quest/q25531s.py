# Created by MechAviv
# Quest ID :: 25531
# Light Reborn
from net.swordie.ms.enums import UIType


sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.sendDelay(1000)


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("To stop the power of darkness, I must first recover the power of light.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I can still feel the light's warmth deep within my heart. The darkness has not hidden it completely...")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I must find my center once more. Remember the teachings of Aurora. Balance, harmony, courage...")

sm.startQuest(25531)
sm.completeQuest(25531)
sm.giveExp(762)
sm.giveAndEquip(1212001)
sm.giveAndEquip(1352400)
sm.giveItem(2001502, 30)
sm.giveItem(2001506, 30)
sm.sendDelay(900)


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("The power of light embraces me! This darkness will not prevail.")


sm.sendDelay(900)


sm.progressMessageFont(3, 20, 20, 0, "Level up to increase your stats. Hotkey [S] / Secondary Key [C]")
sm.sendDelay(1500)


sm.openUI(UIType.UI_STAT)
sm.sendDelay(900)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Stat Changed [16640] Packet: 01 00 00 41 00 00 00 00 00 00 3F 00 00 00 FF 00 00 01 1A 00
# Inventory Operation with 1 operations.
# [SET_ACTIVE_EMOTICON_ITEM] [0A 56 D3 00 00 00 00 00 00 00 00 00 ]
