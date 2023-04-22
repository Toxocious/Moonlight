# Created by MechAviv
# Quest ID :: 25561
# The Power Is Yours
from net.swordie.ms.enums import UIType


sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.sendDelay(1000)


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("I must test the limits of both light and darkness.")


sm.sendDelay(900)


sm.progressMessageFont(3, 20, 20, 0, "Level up to increase your skill level. Skills can be assigned to hotkeys. Hotkey [K]")
sm.sendDelay(1500)


sm.openUIWithOption(UIType.UI_SKILL, 27001201)
sm.sendDelay(1500)


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("I can add Abyssal Drop to my hotkeys and use it to annihilate #b10 Bubblings#k.")


sm.sendDelay(600)


sm.sendDelay(900)
sm.avatarOriented("UI/tutorial.img/8")


sm.startQuest(25561)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)