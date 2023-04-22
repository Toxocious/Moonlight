# Portal for Evan Intro | Utah's House: Attic (100030100)
# Author: Tiger

from net.swordie.ms.constants import GameConstants

# "What a strange dream"
sm.avatarOriented("Effect/OnUserEff.img/guideEffect/evanTutorial/evanBalloon30")
sm.createQuestWithQRValue(GameConstants.EVAN_INTRO, "1") # Custom QR value, so script doesn't repeat the sayImage in the next portal "evanRoom1"
sm.dispose()
