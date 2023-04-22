# Created by MechAviv
# Map ID :: 940012010
# Hidden Street : Decades Later

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.removeSkill(60011219)

if not "1" in sm.getQRValue(25807):
    sm.levelUntil(10)
    sm.setJob(6500)
    sm.createQuestWithQRValue(25807, "1")
    sm.resetStats()
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 C2 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 C2 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00 
    sm.addSP(5, True)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.giveSkill(60011216, 1, 1)
    sm.giveSkill(60011218, 1, 1)
    sm.giveSkill(60011220, 1, 1)
    sm.giveSkill(60011222, 1, 1)
    sm.sendDelay(300)


sm.showFieldEffect("kaiser/text0", 0)
sm.sendDelay(4200)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# [FORCED_STAT_RESET] []
sm.warp(940011020, 0)
