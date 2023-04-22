# Portal for Evan Intro | Utah's House: Attic (100030100)
# Author: Tiger

from net.swordie.ms.constants import GameConstants

if "1" in sm.getQRValue(GameConstants.EVAN_INTRO):
    sm.sendSayImage("UI/tutorial/evan/0/0")
    sm.setQRValue(GameConstants.EVAN_INTRO, "2")

# "What this on the back of my hand? Eh, I won't worry about it"
sm.avatarOriented("Effect/OnUserEff.img/guideEffect/evanTutorial/evanBalloon70")

