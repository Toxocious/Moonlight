# Created by MechAviv
# [Kyrin]  |  [10204]
# Maple Road : Split Road of Destiny

sm.setSpeakerID(10204)
sm.sendNext("Pirates are blessed with outstanding dexterity and power, utilizing their guns for long-range attacks while using their power in melee combat situations. Gunslingers use elemental-based bullets for added damage, while Brawlers transform into a different being for maximum effect.")


sm.setSpeakerID(10204)
if sm.sendAskYesNo("Would you like to experience what it's like to be a Pirate?"):
    sm.setTemporarySkillSet(0)
    sm.createQuestWithQRValue(32219, "4000026")
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    sm.warp(1020500, 0)
else:
    sm.setSpeakerID(10204)
    sm.sendNext("If you wish to experience what it's like to be a Pirate, come see me again.")