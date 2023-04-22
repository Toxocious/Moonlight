# Created by MechAviv
# Quest ID :: 17005
# [Commerci] Bon Voyage 1
from net.swordie.ms.enums import UIType


sm.setSpeakerID(9390220)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSayNextIllustration("Each time you start a voyage, you have to select a destination.", 9390220, True)


# Update Quest Record EX | Quest ID: [17009] | Data: step=1
sm.openUI(UIType.UI_SAILING)
sm.setSpeakerID(9390220)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSayIllustration("The blue locations are the ones available to you. ", 9390220, True)


sm.setSpeakerID(9390220)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSayIllustration("The only place you can go right now is [Dolce]. But trade more, and you'll unlock new places.", 9390220, True)


sm.setSpeakerID(9390220)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSayPrevIllustration("Mouseover each destination to get more information about it. Now, how about heading over to [Dolce]?", 9390220, True)