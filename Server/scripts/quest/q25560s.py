# Created by MechAviv
# Quest ID :: 25560
# Containing Darkness
from net.swordie.ms.enums import UIType


sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.sendDelay(1000)


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Is this how the Black Mage understood the world? I see now that everyone else is beneath me!")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("My soul was almost lost to the power of darkness. I see its appeal, but I would not have it erode my entire being. I will learn to harness it, and make it my own. ")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("But first, I must master my new magic. My old weapons of Light will now be tools of the Dark.")


sm.giveAndEquip(1212001)
sm.giveAndEquip(1352400)
sm.giveItem(2001502, 30)
sm.giveItem(2001506, 30)
sm.startQuest(25560)
sm.completeQuest(25560)
sm.giveExp(1280)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Yes, it feels good to have a weapon in my hands once more. Now, who shall I test my new powers on first...")


sm.sendDelay(900)


sm.progressMessageFont(3, 20, 20, 0, "Level up to increase your stats. Hotkey [S] / Secondary Key [C]")
sm.sendDelay(1500)


sm.openUI(UIType.UI_STAT)
sm.sendDelay(900)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)