# Created by MechAviv
# Map ID :: 940002040
# Hidden Street : Decades Later

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
if not "1" in sm.getQRValue(25807):
    sm.levelUntil(10)
    sm.removeSkill(60000222)
    sm.giveAndEquip(1402177)
    sm.giveAndEquip(1352500)
    sm.giveItem(1142484)
    sm.createQuestWithQRValue(25807, "1")
    sm.setJob(6100)
    sm.removeSkill(60001229)
    sm.removeSkill(60001229)
    # Unhandled Stat Changed [STR] Packet: 00 00 40 00 00 00 00 00 00 00 23 00 FF 00 00 00 00
    # Unhandled Stat Changed [DEX] Packet: 00 00 80 00 00 00 00 00 00 00 04 00 FF 00 00 00 00
    # Unhandled Stat Changed [AP] Packet: 00 00 00 40 00 00 00 00 00 00 17 00 FF 00 00 00 00
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 EE 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 D5 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 D5 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 EE 01 00 00 FF 00 00 00 00
    sm.addSP(3, True)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.giveSkill(60001216, 1, 1)
    sm.giveSkill(60001217, 1, 1)
    sm.giveSkill(60001218, 1, 1)
    sm.giveSkill(60000219, 1, 1)
    sm.giveSkill(60001225, 1, 1)
    sm.giveSkill(61001000, 1, 20)
    sm.giveSkill(61001101, 1, 20)
    sm.setFuncKeyByScript(True, 60001225, 42)
    sm.sendDelay(900)


sm.showFieldEffect("kaiser/text0", 0)
sm.sendDelay(4200)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# [FORCED_STAT_RESET] []
sm.warp(940001200, 0)
