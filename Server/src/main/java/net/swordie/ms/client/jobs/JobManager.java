package net.swordie.ms.client.jobs;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.jobs.adventurer.*;
import net.swordie.ms.client.jobs.adventurer.archer.Archer;
import net.swordie.ms.client.jobs.adventurer.archer.BowMaster;
import net.swordie.ms.client.jobs.adventurer.archer.Marksman;
import net.swordie.ms.client.jobs.adventurer.archer.Pathfinder;
import net.swordie.ms.client.jobs.adventurer.magician.Bishop;
import net.swordie.ms.client.jobs.adventurer.magician.FirePoison;
import net.swordie.ms.client.jobs.adventurer.magician.IceLightning;
import net.swordie.ms.client.jobs.adventurer.magician.Magician;
import net.swordie.ms.client.jobs.adventurer.pirate.*;
import net.swordie.ms.client.jobs.adventurer.thief.BladeMaster;
import net.swordie.ms.client.jobs.adventurer.thief.NightLord;
import net.swordie.ms.client.jobs.adventurer.thief.Shadower;
import net.swordie.ms.client.jobs.adventurer.thief.Thief;
import net.swordie.ms.client.jobs.adventurer.warrior.DarkKnight;
import net.swordie.ms.client.jobs.adventurer.warrior.Hero;
import net.swordie.ms.client.jobs.adventurer.warrior.Paladin;
import net.swordie.ms.client.jobs.adventurer.warrior.Warrior;
import net.swordie.ms.client.jobs.anima.HoYoung;
import net.swordie.ms.client.jobs.cygnus.*;
import net.swordie.ms.client.jobs.flora.Adele;
import net.swordie.ms.client.jobs.flora.Ark;
import net.swordie.ms.client.jobs.flora.Illium;
import net.swordie.ms.client.jobs.legend.*;
import net.swordie.ms.client.jobs.nova.AngelicBuster;
import net.swordie.ms.client.jobs.nova.Cadena;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.*;
import net.swordie.ms.client.jobs.resistance.demon.Demon;
import net.swordie.ms.client.jobs.resistance.demon.DemonAvenger;
import net.swordie.ms.client.jobs.resistance.demon.DemonSlayer;
import net.swordie.ms.client.jobs.sengoku.Hayato;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.connection.InPacket;

import java.lang.reflect.InvocationTargetException;

/**
 * Created on 12/14/2017.
 */
public class JobManager {
    private static final Class[] jobClasses = new Class[]{
            Warrior.class,
            Hero.class,
            Paladin.class,
            DarkKnight.class,

            Magician.class,
            FirePoison.class,
            IceLightning.class,
            Bishop.class,

            Archer.class,
            BowMaster.class,
            Marksman.class,
            Pathfinder.class,

            Thief.class,
            NightLord.class,
            Shadower.class,
            BladeMaster.class,

            HoYoung.class,

            Pirate.class,
            Buccaneer.class,
            Corsair.class,
            Cannonneer.class,
            Jett.class,

            BeastTamer.class,
            Beginner.class,
            Kinesis.class,
            PinkBean.class,

            BlazeWizard.class,
            DawnWarrior.class,
            Mihile.class,
            NightWalker.class,
            Noblesse.class,
            ThunderBreaker.class,
            WindArcher.class,

            Aran.class,
            Evan.class,
            Legend.class,
            Luminous.class,
            Mercedes.class,
            Phantom.class,
            Shade.class,

            AngelicBuster.class,
            Kaiser.class,
            Cadena.class,

            Demon.class,
            DemonSlayer.class,
            DemonAvenger.class,

            BattleMage.class,
            Blaster.class,
            Citizen.class,
            Mechanic.class,
            WildHunter.class,
            Xenon.class,

            Hayato.class,
            Kanna.class,

            Illium.class,
            Ark.class,
            Adele.class,

            Zero.class
    };

    private short id;

    public static void handleSkill(Char chr, InPacket inPacket) {
        for (Class clazz : jobClasses) {
            Job job = null;
            try {
                job = (Job) clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (job != null && job.isHandlerOfJob(chr.getJob())) {
                inPacket.decodeInt(); // crc
                int skillID = inPacket.decodeInt();
                int slv = inPacket.decodeByte();
                job.handleSkill(chr, chr.getTemporaryStatManager(), skillID, slv, inPacket, new SkillUseInfo());
            }
        }
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public static Job getJobById(short id, Char chr) {
        Job job = null;
        for (Class clazz : jobClasses) {
            try {
                job = (Job) clazz.getConstructor(Char.class).newInstance(chr);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
            if (job != null && job.isHandlerOfJob(id)) {
                return job;
            }
        }
        return job;
    }
}
