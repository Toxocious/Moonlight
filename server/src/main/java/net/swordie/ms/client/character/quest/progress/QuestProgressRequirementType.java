package net.swordie.ms.client.character.quest.progress;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created on 3/8/2018.
 */
public enum QuestProgressRequirementType {
    ITEM(0),
    LEVEL(1),
    MOB(2),
    MONEY(3)
    ;

    private byte val;

    QuestProgressRequirementType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }

    public static QuestProgressRequirementType getQPRTByObj(Object o) {
        return o instanceof QuestProgressItemRequirement ? ITEM :
                o instanceof QuestProgressLevelRequirement ? LEVEL :
                o instanceof QuestProgressMobRequirement ? MOB :
                o instanceof QuestProgressMoneyRequirement ? MONEY : null;
    }

    public static QuestProgressRequirementType getQPRTByVal(byte val) {
        return Arrays.stream(QuestProgressRequirementType.values())
                .filter(qprt -> qprt.getVal() == val).findFirst().orElse(null);
    }

    public QuestProgressRequirement load(DataInputStream dis) throws IOException {
        switch(this) {
            case ITEM:
                return (QuestProgressRequirement) new QuestProgressItemRequirement().load(dis);
            case LEVEL:
                return (QuestProgressRequirement) new QuestProgressLevelRequirement(0).load(dis);
            case MOB:
                return (QuestProgressRequirement) new QuestProgressMobRequirement().load(dis);
            case MONEY:
                return (QuestProgressRequirement) new QuestProgressMoneyRequirement(0).load(dis);
            default:
                return null;
        }
    }
}
