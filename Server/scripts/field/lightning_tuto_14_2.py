# Created by MechAviv
# ID :: [910141030]
# Hidden Street : Front Yard Wreckage
if chr.getJob() == 2004:
    OBJECT_1 = sm.getIntroNpcObjectID(1032203)
    sm.setQuestEx(25505, "route", "1")
    sm.sendDelay(1000)
    sm.levelUntil(10)
    sm.removeSkill(20040218)
    sm.removeSkill(20041226)
    sm.setJob(2700)
    sm.giveSkill(20040216, 1, 1)
    sm.giveSkill(20040217, 1, 1)
    sm.giveSkill(20040221, 1, 1)
    sm.giveSkill(20041222, 1, 1)
    # Unhandled Stat Changed [INSIGHT_EXP] Packet: 00 00 00 00 20 00 00 00 00 00 D3 11 00 00 FF 00 00 00 00
    # Unhandled Message [INC_NON_COMBAT_STAT_EXP_MESSAGE] Packet: 14 00 00 20 00 00 00 00 00 D3 11 00 00
    # Unhandled Stat Changed [WILL_EXP] Packet: 00 00 00 00 40 00 00 00 00 00 D3 11 00 00 FF 00 00 00 00
    # Unhandled Message [INC_NON_COMBAT_STAT_EXP_MESSAGE] Packet: 14 00 00 40 00 00 00 00 00 D3 11 00 00
    sm.resetStats()
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 C2 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 07 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 07 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 52 02 00 00 FF 00 00 00 00
    sm.giveSkill(27001201, 1, 20)
    sm.giveSkill(27000207, 1, 5)
    sm.addSP(3, True)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.giveItem(1142479)
    sm.giveAndEquip(1052497)
    sm.giveAndEquip(1072702)
    sm.giveAndEquip(1102444)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.sendNpcController(OBJECT_1, False)
    sm.warp(101020100, 9)