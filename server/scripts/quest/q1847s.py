# 1847s - [Evolution System] System Enhancement Mode 1
from net.swordie.ms.enums import UIType
sm.setSpeakerID(9075202)
if sm.sendAskYesNo("Initiating system enhancement mode. Would you like to operate the Evolution System? You will be connected to a much more enhanced virtual world."):
    sm.openUI(UIType.UI_EVOLVING_SYSTEM)
    sm.startQuest(parentID)
else:
    sm.dispose()