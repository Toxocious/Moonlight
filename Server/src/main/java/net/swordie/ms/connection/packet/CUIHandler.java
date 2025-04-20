package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.union.UnionBoard;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;

import java.util.List;
import java.util.Set;
import net.swordie.ms.client.character.union.UnionMember;

/**
 * @author Sjonnie
 * Created on 10/10/2018.
 */
public class CUIHandler {

    public static OutPacket unionResult(int coin, int rank, Set<Char> eligibleChars, UnionBoard activeBoard,
                                        UnionMember mobileMember, UnionMember labMember, UnionMember labEnhancedMember) {
        OutPacket outPacket = new OutPacket(OutHeader.UNION_RESULT);
        
        outPacket.encodeInt(coin);
        outPacket.encodeInt(rank);

        outPacket.encodeInt(eligibleChars.size());
        for (Char chr : eligibleChars) {
            chr.createUnionMember().encode(outPacket, true);
        }

        outPacket.encodeInt(activeBoard.getActiveMembers().size());
        for (UnionMember unionMember : activeBoard.getActiveMembers()) {
            unionMember.encode(outPacket);
        }
        outPacket.encodeByte(mobileMember != null);
        if (mobileMember != null) {
            mobileMember.encode(outPacket);
        }
        outPacket.encodeByte(labMember != null);
        if (labMember != null) {
            labMember.encode(outPacket);
        }
        outPacket.encodeByte(labEnhancedMember != null);
        if (labEnhancedMember != null) {
            labEnhancedMember.encode(outPacket);
        }
        return outPacket;
    }

    public static OutPacket violetCubeResult(int type, int result, int line, List<Integer> options) {
        OutPacket outPacket = new OutPacket(OutHeader.VIOLET_CUBE_RESULT);
        outPacket.encodeShort(type);
        if (type == 0 || type == 2) { // select
            outPacket.encodeInt(result);
            if (type == 0) {
                outPacket.encodeInt(line); // selectable
            } else {
                outPacket.encodeLong(0); // ??
            }
            outPacket.encodeInt(options.size());
            for (int option : options) {
                outPacket.encodeInt(option);
            }
        } else if (type == 1) { // result
            outPacket.encodeInt(result); // error code? 0: success
        }
        return outPacket;
    }


    public static OutPacket unionAssignResult(int rank, Set<Char> eligibleChars, UnionBoard activeBoard,
                                              UnionMember mobileMember, UnionMember labMember, UnionMember labEnhancedMember) {
        OutPacket outPacket = new OutPacket(OutHeader.UNION_ASSIGN_RESULT);

        outPacket.encodeInt(rank);

        outPacket.encodeInt(eligibleChars.size());
        for (Char chr : eligibleChars) {
            chr.createUnionMember().encode(outPacket, true);
        }

        outPacket.encodeInt(activeBoard.getActiveMembers().size());
        for (UnionMember unionMember : activeBoard.getActiveMembers()) {
            unionMember.encode(outPacket, false);
        }
        outPacket.encodeByte(mobileMember != null);
        if (mobileMember != null) {
            mobileMember.encode(outPacket, false);
        }
        outPacket.encodeByte(labMember != null);
        if (labMember != null) {
            labMember.encode(outPacket, false);
        }
        outPacket.encodeByte(labEnhancedMember != null);
        if (labEnhancedMember != null) {
            labEnhancedMember.encode(outPacket, false);
        }
        return outPacket;
    }
    
    public static OutPacket unionCoin(int coin) {
        OutPacket outPacket = new OutPacket(OutHeader.UNION_COIN);

        outPacket.encodeInt(0); // Unknown
        outPacket.encodeInt(coin);

        return outPacket;
    }
    
    public static OutPacket unionRaidDamage(long damage) {
        OutPacket outPacket = new OutPacket(OutHeader.UNION_RAID_DAMAGE);

        outPacket.encodeLong(damage);

        return outPacket;
    }
    
    public static OutPacket unionRaidCoin(int coin) {
        OutPacket outPacket = new OutPacket(OutHeader.UNION_RAID_COIN);

        outPacket.encodeInt(coin);
        outPacket.encodeByte(0); // Unknown

        return outPacket;
    }
    
    public static OutPacket unionRaidBossUpdate(int template, int template2, long curHP, long maxHP, long curHP2, long maxHP2) {
        OutPacket outPacket = new OutPacket(OutHeader.UNION_RAID_BOSS_UPDATE);

        outPacket.encodeInt(template);
        outPacket.encodeLong(curHP);
        outPacket.encodeLong(maxHP);
        outPacket.encodeInt(template2);
        outPacket.encodeLong(curHP2);
        outPacket.encodeLong(maxHP2);

        return outPacket;
    }
}
