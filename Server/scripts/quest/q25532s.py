# Created by MechAviv
# Quest ID :: 25532
# Rehabilitation
from net.swordie.ms.enums import UIType


sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.sendDelay(1000)


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("I must never turn from the Light again. My full power is not yet restored, but it is only a matter of time.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("But Lania will be easy prey to the monsters here when I'm gone. I should see to them before I leave.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("My enemies await on the other side of that portal. My Light will cleanse them from Maple World!")

sm.startQuest(25532)
sm.sendDelay(900)


sm.progressMessageFont(3, 20, 20, 0, "Level up to increase your skill level. Skills can be assigned to hotkeys. Hotkey [K]")
sm.sendDelay(1500)


sm.openUIWithOption(UIType.UI_SKILL, 27001100)
sm.sendDelay(1500)


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("You should level up Flash Shower, your basic attack skill. Assign it to your hotkeys and get ready to use it.")


sm.sendDelay(600)


sm.sendDelay(900)
sm.avatarOriented("UI/tutorial.img/8")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)