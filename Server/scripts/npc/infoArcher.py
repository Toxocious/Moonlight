# Created by MechAviv
# [Athena Pierce]  |  [10200]
# Maple Road : Split Road of Destiny

sm.setSpeakerID(10200)
sm.sendNext("Bowmen are blessed with dexterity and power, taking charge of long-distance attacks, providing support for those at the front line of the battle. Very adept at using landscape as part of the arsenal.")


sm.setSpeakerID(10200)
if sm.sendAskYesNo("Would you like to experience what it's like to be a Bowman?"):
    sm.setTemporarySkillSet(0)

    sm.createQuestWithQRValue(32219, "4000026")
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    sm.warp(1020300, 0)
else:
    sm.setSpeakerID(10200)
    sm.sendNext("If you wish to experience what it's like to be a Bowman, come see me again.")


