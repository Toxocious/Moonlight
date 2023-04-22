package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 5/26/2018.
 */
public enum QuestType {
    QuestReq_LostItem(0),
    QuestReq_AcceptQuest(1),
    QuestReq_CompleteQuest(2),
    QuestReq_ResignQuest(3),
    QuestReq_OpeningScript(4),
    QuestReq_CompleteScript(5),
    QuestReq_LaterStep(6),

    QuestRes_Start_QuestTimer(7),
    QuestRes_End_QuestTimer(8),
    QuestRes_Start_TimeKeepQuestTimer(9),
    QuestRes_End_TimeKeepQuestTimer(10),

    QuestRes_Act_Success(11),
    QuestRes_Act_Failed_Unknown(12),
    QuestRes_Act_Failed_Inventory(13),
    QuestRes_Act_Failed_Meso(14),
    QuestRes_Act_Failed_OverflowMeso(15),
    QuestRes_Act_Failed_Pet(16),
    QuestRes_Act_Failed_Equipped(17),
    QuestRes_Act_Failed_OnlyItem(18),
    QuestRes_Act_Failed_TimeOver(19),
    QuestRes_Act_Failed_State(20),
    QuestRes_Act_Failed_Quest(21),
    QuestRes_Act_Failed_Block(22),
    QuestRes_Act_Failed_Universe(23),
    QuestRes_Act_Reset_QuestTimer(24),

    MakingRes_Success_SoSo(25),
    MakingRes_Success_Good(26),
    MakingRes_Success_Cool(27),
    MakingRes_Fail_Unknown(28),
    MakingRes_Fail_Prob(29),
    MakingRes_Fail_NoDecomposer(30),
    MakingRes_Fail_MesoOverflow(31),
    MakingRes_Fail_TooHighFee(32),
    MakingRes_Fail_NotEnoughMeso(33),
    ;

    private byte val;

    QuestType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }

    public static QuestType getQTFromByte(byte type) {
        return Arrays.stream(values()).filter(qt -> qt.getVal() == type).findAny().orElse(null);
    }
}
