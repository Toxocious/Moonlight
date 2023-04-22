# Created by MechAviv
# [Grendel the Really Old]  |  [10201]
# Maple Road : Split Road of Destiny

sm.setSpeakerID(10201)
sm.sendNext("Magicians are armed with flashy element-based spells and secondary magic that aids party as a whole. After the 2nd job adv., the elemental-based magic will provide ample amount of damage to enemies of opposite element.")


sm.setSpeakerID(10201)
if sm.sendAskYesNo("Would you like to experience what it's like to be a Magician?"):
    sm.setTemporarySkillSet(0)
    sm.createQuestWithQRValue(32219, "4000026")
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    sm.warp(1020200, 0)
else:
    sm.setSpeakerID(10201)
    sm.sendNext("If you wish to experience what it's like to be a Magician, come see me again.")


