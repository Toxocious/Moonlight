# Portal for Evan Intro | Hidden Street: Lush Forest (900020110)
# Author: Tiger

from net.swordie.ms.constants import GameConstants

# Custom QR value to prevent sayImage from loop repeatedly when portal script is run

if not "2" in sm.getQRValue(GameConstants.EVAN_INTRO2):
    sm.createQuestWithQRValue(GameConstants.EVAN_INTRO2, "1")

if "1" in sm.getQRValue(GameConstants.EVAN_INTRO2):
    sm.sendSayImage("UI/tutorial/evan/8/0")
    sm.setQRValue(GameConstants.EVAN_INTRO2, "2")

