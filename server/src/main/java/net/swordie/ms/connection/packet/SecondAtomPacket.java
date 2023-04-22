package net.swordie.ms.connection.packet;


import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.SecondAtom;
import net.swordie.ms.client.jobs.flora.Adele;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SecondAtomPacket {


    public static OutPacket createSecondAtom(int charID, List<SecondAtom> secondAtoms) { //TODO grab info from WZ || make this not look like hot ass

        OutPacket outPacket = new OutPacket(OutHeader.CREATE_SECOND_ATOM);

        outPacket.encodeInt(charID);

        secondAtoms.forEach(it -> {
            it.encode(outPacket);
        });

        return outPacket;
    }

    public static OutPacket createSecondAtoms(int charID, List<SecondAtom> secondAtoms) { //TODO grab info from WZ || make this not look like hot ass
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_INFINITY_BLADE_ATOM);
        outPacket.encodeInt(charID);

        outPacket.encodeInt(secondAtoms.size()); //Size of SecondAtoms
        AtomicBoolean infinity = new AtomicBoolean(false);

        secondAtoms.forEach(it -> {
            //it.encode(outPacket);
            outPacket.encodeInt(it.getObjectID());
            outPacket.encodeInt(it.getDataIndex()); //DataIndex || 1-6 Swords that chill | 7 Actual Sword | 8 V Job Sword
            outPacket.encodeInt(it.getKey()); //Key
            outPacket.encodeInt(it.getCharId());
            outPacket.encodeInt(it.getTargetID()); //Target

            outPacket.encodeInt(it.getCreateDel()); //Create
            outPacket.encodeInt(it.getEnableDel()); //Enable
            outPacket.encodeInt(0); //Rotate
            outPacket.encodeInt(it.getSkillId()); //skillID //151001001
             if (it.getSkillId() == Adele.INFINITY_BLADE) {
                 infinity.set(true);
             }
            outPacket.encodeInt(10); //Slv

            outPacket.encodeInt(it.getExpire()); //Expire
            outPacket.encodeInt(0); //CustomRotate
            outPacket.encodeInt(it.getMaxAtt()); //AttAcount

            outPacket.encodePositionInt(it.getPosition());

            outPacket.encodeByte(false);

            outPacket.encodeByte(0);

            int size = 0;
            outPacket.encodeInt(size);
            for (int z = 0; z < size; z++) {
                outPacket.encodeInt(0);
            }
        });

        outPacket.encodeInt(infinity.get() ? 1 : 0);

        return outPacket;
    }



    public static OutPacket createInfinityAtom(SecondAtom sfa) { //TODO grab info from WZ || make this not look like hot ass


        OutPacket outPacket = new OutPacket(OutHeader.CREATE_INFINITY_BLADE_ATOM);
        outPacket.encodeInt(sfa.getCharId());

        outPacket.encodeInt(1); //Size of SecondAtoms

        outPacket.encodeInt(sfa.getObjectID());
        outPacket.encodeInt(sfa.getDataIndex()); //DataIndex || 1-6 Swords that chill | 7 Actual Sword | 8 V Job Sword
        outPacket.encodeInt(sfa.getKey()); //Key
        outPacket.encodeInt(sfa.getCharId());
        outPacket.encodeInt(sfa.getTargetID()); //Target

        outPacket.encodeInt(sfa.getCreateDel()); //Create
        outPacket.encodeInt(sfa.getEnableDel()); //Enable
        outPacket.encodeInt(0); //Rotate
        outPacket.encodeInt(sfa.getSkillId()); //skillID //151001001
        outPacket.encodeInt(sfa.getSkilllv()); //Slv

        outPacket.encodeInt(sfa.getExpire()); //Expire
        outPacket.encodeInt(0); //CustomRotate
        outPacket.encodeInt(sfa.getMaxAtt()); //AttAcount

        outPacket.encodePositionInt(sfa.getPosition());

        outPacket.encodeByte(false);

        outPacket.encodeInt(0);
        outPacket.encodeInt(0);

        outPacket.encodeByte(0);


        int size = 0;
        outPacket.encodeInt(size);
        for (int z = 0; z < size; z++) {
            outPacket.encodeInt(0);
        }

        outPacket.encodeInt(sfa.getSkillId() == Adele.INFINITY_BLADE ? 1 : 0);

        return outPacket;
    }

    public static OutPacket secondAtomAttack(Char chr, int fakey, int level) {
        OutPacket outPacket = new OutPacket(OutHeader.SECOND_ATOM_ATTACK);

        outPacket.encodeInt(chr.getId());

        outPacket.encodeInt(fakey);
        outPacket.encodeInt(level); //Animate?

        return outPacket;
    }

    public static OutPacket removeSecondAtom(Char chr, int oid) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOVE_SECOND_ATOM);

        outPacket.encodeInt(chr.getId());

        int size = 1; //AtomSize
        outPacket.encodeInt(size);
        for (int z = 0; z < size; z++) {
            outPacket.encodeInt(oid);
        }
        outPacket.encodeInt(oid);
        outPacket.encodeInt(oid); //new unk

        return outPacket;
    }


}

