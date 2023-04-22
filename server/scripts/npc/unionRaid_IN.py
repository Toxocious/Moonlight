#
# @author PacketBakery
# @npc Dame Appropiation - Legion Manager
#

from net.swordie.ms.enums import UIType

if sm.sendAskYesNo("#r#eA duel with a dragon#n#k awaits you! Be careful, "
                   "he's a big'un.\r\nWould you like to #b#eenter the Legion Raid#n#k?"):
    sm.createQuestWithQRValue(16014, "map=" + str(sm.getFieldID()))
    sm.closeUI(UIType.UNION_BOARD)
    sm.warp(921172000)
else:
    sm.sendSayOkay("Talk to me after you've thought it over.")
