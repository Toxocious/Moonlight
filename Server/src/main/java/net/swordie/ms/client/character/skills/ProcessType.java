package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.container.Tuple;

import java.util.HashSet;

/**
 * Created on 11-4-2019.
 */
public class ProcessType {

    public SkillUseInfo skillUseInfo;

    public ProcessType(SkillUseInfo skillUseInfo) {
        this.skillUseInfo = skillUseInfo;
    }

    public void decode(InPacket inPacket) {
        inPacket.decodeInt(); // unk
        boolean bool = inPacket.decodeByte() != 0;
        if (bool) {
            int processType = inPacket.decodeInt();
            while (processType != -1) {
                if (processType >= 1 && processType <= 16 || processType >= 19 && processType <= 22) {
                    bool = inPacket.decodeByte() != 0;
                    if (bool) {
                        switch (processType) {
                            case 1:
                                inPacket.decodeInt();
                                inPacket.decodeInt();
                                inPacket.decodeInt();
                                break;
                            case 2:
                                inPacket.decodeByte();
                                inPacket.decodeByte();
                                skillUseInfo.skillId = inPacket.decodeInt(); // skillId
                                inPacket.decodeInt();

                                //new?
                                inPacket.decodeByte();
                                skillUseInfo.endingPosition = inPacket.decodePositionInt(); // spawn locations
                                break;
                            case 3:
                                inPacket.decodeByte();
                                inPacket.decodeInt();
                                break;
                            case 7:
                                inPacket.decodeInt();
                                inPacket.decodeInt();
                                inPacket.decodeByte();
                                break;
                            case 8:
                                skillUseInfo.rect = inPacket.decodeIntRect(); // rect probably
                                break;
                            case 9:
                                inPacket.decodeInt();
                                skillUseInfo.endingPosition = inPacket.decodePositionInt(); // ending Position
                                skillUseInfo.isLeft = inPacket.decodeInt() == -1; // isLeft   -1 = Left  |  1 = Right
                                break;
                            case 20:
                                inPacket.decodeInt();
                                inPacket.decodeInt();
                                inPacket.decodeInt();
                                inPacket.decodeInt();
                                inPacket.decodeInt();
                                inPacket.decodeInt();
                                inPacket.decodeInt();
                                inPacket.decodeInt();
                                break;
                            case 21:
                                int size = inPacket.decodeInt();
                                for (int i = 0; i < size; i++) {
                                    inPacket.decodeInt();
                                    inPacket.decodeInt();
                                    inPacket.decodeInt();
                                }
                                break;
                            case 22:
                                skillUseInfo.shardsPositions = new HashSet<>();
                                int shardsSize = inPacket.decodeInt();
                                for (int i = 0; i < shardsSize; i++) {
                                    int mobObjectId = inPacket.decodeInt();
                                    Position position = inPacket.decodePositionInt();
                                    skillUseInfo.shardsPositions.add(new Tuple<>(mobObjectId, position));
                                }
                                break;
                        }
                    }
                }
                processType = inPacket.decodeInt();
            }
        }
    }
}
