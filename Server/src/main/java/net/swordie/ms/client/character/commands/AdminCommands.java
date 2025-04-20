package net.swordie.ms.client.character.commands;

import net.swordie.ms.Server;
import net.swordie.ms.client.User;
import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.avatar.AvatarData;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.items.*;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.StolenSkill;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.friend.FriendshipRingRecord;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.guild.GuildMember;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyResult;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.*;
import net.swordie.ms.constants.JobConstants.JobEnum;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.mob.ForcedMobStat;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.loaders.*;
import net.swordie.ms.loaders.containerclasses.ItemSet;
import net.swordie.ms.loaders.containerclasses.SkillStringInfo;
import net.swordie.ms.scripts.ScriptManagerImpl;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.*;
import net.swordie.ms.world.Channel;
import net.swordie.ms.world.World;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;
import net.swordie.ms.world.field.Portal;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.NotDamaged;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.RideVehicle;

import static net.swordie.ms.enums.AccountType.*;
import static net.swordie.ms.enums.ChatType.*;
import static net.swordie.ms.enums.InventoryOperation.Add;

/**
 * Created on 12/22/2017.
 */
public class AdminCommands {
    static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    @Command(names = {"help"}, description = "Displays all commands available to you.", requiredType = Player)
    public static class Help extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            for (Class clazz : AdminCommands.class.getClasses()) {
                Command cmd = (Command) clazz.getAnnotation(Command.class);
                if (chr.getUser().getAccountType().ordinal() >= cmd.requiredType().ordinal()) {
                    StringBuilder str = new StringBuilder(String.format("[%s] ", cmd.requiredType()));
                    String[] names = cmd.names();
                    for (int i = 0; i < names.length; i++) {
                        String cmdName = names[i];
                        str.append(cmdName);
                        if (i != names.length - 1) {
                            str.append(", ");
                        }
                    }
                    if (!cmd.description().isEmpty()) {
                        String description = cmd.description();
                        str.append(": " + description);
                    }

                    chr.chatMessage(Expedition, str.toString());
                }
            }
        }
    }


    @Command(names = {"hair"}, requiredType = Tester)
    public static class hair extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            final int hair = Integer.parseInt(args[1]);
            AvatarLook avatar = chr.getAvatarData().getAvatarLook();
            avatar.setHair(hair);
            CharacterStat characterStat = chr.getAvatarData().getCharacterStat();
            characterStat.setHair(hair);
            Map<Stat, Object> stats = new HashMap<>();
            stats.put(Stat.hair, hair);
            chr.getClient().write(WvsContext.statChanged(stats));
            byte maskValue = AvatarModifiedMask.AvatarLook.getVal();
            chr.getField().broadcastPacket(UserRemote.avatarModified(chr, maskValue, (byte) 0), chr);
        }
    }

    @Command(names = {"face"}, requiredType = Tester)
    public static class face extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            final int face = Integer.parseInt(args[1]);
            AvatarLook avatar = chr.getAvatarData().getAvatarLook();
            avatar.setFace(face);
            CharacterStat characterStat = chr.getAvatarData().getCharacterStat();
            characterStat.setFace(face);
            Map<Stat, Object> stats = new HashMap<>();
            stats.put(Stat.face, face);
            chr.getClient().write(WvsContext.statChanged(stats));
            byte maskValue = AvatarModifiedMask.AvatarLook.getVal();
            chr.getField().broadcastPacket(UserRemote.avatarModified(chr, maskValue, (byte) 0), chr);
        }
    }

    @Command(names = {"test"}, requiredType = Tester)
    public static class test extends AdminCommand {
        public static void execute(Char chr, String[] args) {
          chr.write(WvsContext.incWP(1));
        }
    }


    @Command(names = {"warphere"}, requiredType = Tester)
    public static class WarpHere extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            Char victim = chr.getWorld().getCharByName((args[1]));
            if (victim != null) {
                victim.changeChannelAndWarp(chr.getClient().getChannelInstance().getChannelId(), chr.getFieldID());
            } else {
                chr.chatMessage(Notice2, "Player not found, make sure you typed the correct name (Case Sensitive).");
            }
        }
    }

    @Command(names = {"rotatecamera"}, description = "Rotates your camera to given angle.", requiredType = Tester)
    public static class RotateCamera extends AdminCommand {
        public static void execute(Char chr, String[] args) {

            int degree = Integer.parseInt(args[1]);
            chr.getField().broadcastPacket(UserLocal.rotateCamera(degree, 1800));
            //   chr.getField().broadcastPacket(UserLocal.rotateCamera(40 , 800));
            //  chr.getField().broadcastPacket(UserLocal.rotateCamera(40 , 400));
            //  chr.getField().broadcastPacket(UserLocal.rotateCamera(20 , 800));
        }
    }

    @Command(names = {"sell"}, requiredType = AccountType.Player)
    public static class SellItem extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            ScriptManagerImpl smi = chr.getScriptManager();
            smi.startScript(0, "inv-seller", ScriptType.Npc);
        }
    }

    @Command(names = {"packet"}, requiredType = Admin)
    public static class TestPacket extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 4) {
                chr.chatMessage("Usage: !packet <op> <data>");
                return;
            }
            OutPacket outPacket = new OutPacket(Short.parseShort(args[1]));
            outPacket.encodeByte(Integer.parseInt(args[2]));
            outPacket.encodeInt(Integer.parseInt(args[3]));
            outPacket.encodeByte(Integer.parseInt(args[4]));

            chr.write(outPacket);

            /*
            if (args.length < 3) {
                chr.chatMessage("Usage: !packet <op> <data>");
                return;
            }
            OutPacket outPacket = new OutPacket(Short.parseShort(args[1]));
            StringBuilder data = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                data.append(" ").append(args[i]);
            }
            outPacket.encodeArr(data.toString());
            chr.write(outPacket);
             */
        }
    }


    @Command(names = {"usects", "cts"}, requiredType = Tester)
    public static class CtsCom extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Option o = new Option();
            o.nOption = Integer.parseInt(args[2]);
            o.rOption = 2111011;
            o.tOption = 5;

            CharacterTemporaryStat cts = CharacterTemporaryStat.getByBitPos(Integer.parseInt(args[1]));
//            CharacterTemporaryStat cts = CharacterTemporaryStat.MagnetArea;
            if (cts == null) {
                chr.chatMessage("Could not find cts with bitpos " + args[1]);
                return;
            }
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            tsm.putCharacterStatValue(cts, o);
            tsm.sendSetStatPacket();
            System.out.println(String.format("CTS %s = %s", args[1], cts));
        }
    }

    @Command(names = {"fifthjob", "V"}, requiredType = Tester)
    public static class V extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.getQuestManager().completeQuest(QuestConstants.FIFTH_JOB_QUEST);
        }
    }

    @Command(names = {"showinvinfo", "invinfo"}, requiredType = Tester)
    public static class ShowInvInfo extends AdminCommand {

        public static void execute(Char chr, String[] args) {

            chr.chatMessage(WorldName, "------------------------------------------------------------");
            for (InvType invType : InvType.values()) {
                chr.chatMessage(WorldName, invType.toString());
                for (Item item : chr.getInventoryByType(invType).getItems()) {
                    item.setInvType(invType);
                    String name = StringData.getItemStringById(item.getItemId());
                    chr.chatMessage(WorldName, String.format("%s, %d, %d, %d, %s", name, item.getItemId(), item.getId(),
                            item.getBagIndex(), item.getInvType().toString()));
                }
            }
        }
    }

    @Command(names = {"equipinfo"}, requiredType = Tester)
    public static class EquipInfo extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Map<Integer, Integer> activeSetEffects = chr.getActiveSetEffects();

            for (Map.Entry<Integer, Integer> sets : activeSetEffects.entrySet()) {
                ItemSet set = ItemData.getItemSetById(sets.getKey());

                chr.chatMessage("%d - %s: - equippedCount: %d\n", set.getId(), set.getName(), sets.getValue());

                for (Integer i = 1; i <= sets.getValue(); i++) {
                    if (!set.getEffects().containsKey(i)) continue;
                    Map<BaseStat, Double> effects = set.getEffects().get(i);

                    for (Map.Entry<BaseStat, Double> stat : effects.entrySet()) {
                        boolean additive = !stat.getKey().isNonAdditiveStat();
                        chr.chatMessage("stat: %s, value: %f additive: %b\n", stat.getKey(), stat.getValue(), additive);
                    }
                }
            }
        }
    }

    @Command(names = {"testcts"}, requiredType = Admin)
    public static class TestCTS extends AdminCommand {

        public static void execute(Char chr, String[] args) {

//            WildHunterInfo wi = new WildHunterInfo();
//            wi.setIdx((byte) 1);
//            wi.setRidingType((byte) 1);
//            chr.write(WvsContext.wildHunterInfo(wi));
//            new TemporaryStatManager(null).encodeForLocal(null);
            CharacterTemporaryStat cts = Arrays.stream(CharacterTemporaryStat.values()).filter(ctsa -> ctsa.getVal() == Integer.parseInt(args[1]))
                    .findFirst().orElse(null);
            if (cts == null) {
                chr.chatMessage("Could not find a cts with value " + args[1]);
            }
//            CharacterTemporaryStat cts2 = CharacterTemporaryStat.Speed;
//
            OutPacket outPacket = new OutPacket(OutHeader.TEMPORARY_STAT_SET);

//            tsm.encodeForLocal(outPacket);
            // Start encodeForLocal
            int[] mask = new int[CharacterTemporaryStat.length];
            mask[cts.getPos()] |= cts.getVal();
            for (int i = 0; i < mask.length; i++) {
                outPacket.encodeInt(mask[i]);
            }
            log.debug("[Out]\t| " + outPacket);

            outPacket.encodeShort(1); // n                            //Short / Int
            outPacket.encodeInt(Kaiser.FINAL_TRANCE); // r
            outPacket.encodeInt(30000); // t

            //outPacket.encodeInt(0);

            short size = 0;
            outPacket.encodeShort(0);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); // nKey
                outPacket.encodeByte(0); // bEnable
            }
            outPacket.encodeByte(0); // defenseAtt
            outPacket.encodeByte(0); // defenseState
            outPacket.encodeByte(0); // pvpDamage
            outPacket.encodeInt(0); // viperCharge
            // Start TSTS encode
//            outPacket.encodeArr(new byte[Integer.parseInt(args[2])]);
//            outPacket.encodeInt(1);
//            outPacket.encodeInt(80001001);
//            outPacket.encodeByte(1);
//            outPacket.encodeByte(0);
//            outPacket.encodeArr(new byte[Integer.parseInt(args[1])]);
//            outPacket.encodeShort(1);
            // End TSTS encode
            // End  encodeForLocal
            outPacket.encodeInt(0); // indie?
            outPacket.encodeShort(0); // invalid value => "Failed to use the skill for an unknown reason"
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            outPacket.encodeByte(0); // movement enhancing
//            outPacket.encodeArr(new byte[Integer.parseInt(args[1])]);
            chr.write(outPacket);


        }
    }

    @Command(names = {"checkid", "getid", "charid"}, requiredType = Tester)
    public static class CheckId extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.chatMessage(SpeakerChannel, "your charID = " + chr.getId() + " \r\nYour userID = " + chr.getUserId());
        }
    }

    @Command(names = {"tag"}, requiredType = Tester)
    public static class tag extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.write(WvsContext.updateEventNameTag(new int[]{1, 2, 3}));
           chr.write(WvsContext.acquireEventNameTag());
        }
    }

    @Command(names = {"testaa", "aa"}, requiredType = Tester)
    public static class TestAffectedArea extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 4) {
                chr.chatMessage("Usage: !aa <skillid> <slv> <delay>");
                return;
            }
            int skillId = Integer.parseInt(args[1]);
            int slv = Integer.parseInt(args[2]);
            int delay = Integer.parseInt(args[3]);
            AffectedArea aa = new AffectedArea(0);
            aa.setSkillID(skillId);
            aa.setSlv(slv);
            aa.setRect(chr.getPosition().getRectAround(new Rect(-70, -170, 70, 10)));
            aa.setDuration(10);
            aa.setFh(chr.getFoothold());
            aa.setDelay((short) delay);
            chr.getField().spawnLife(aa, null);
        }
    }

    @Command(names = {"getphantomstolenskills"}, requiredType = Tester)
    public static class GetPhantomStolenSkills extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.getStolenSkills().stream().sorted(Comparator.comparing(StolenSkill::getPosition))
                    .forEach(ss ->
                            chr.chatMessage(GroupFriend, "[StolenSkills]  Skill ID: " + ss.getSkillid() + " on Position: " + ss.getPosition() + " with Current level: " + ss.getCurrentlv()));
        }
    }

    @Command(names = {"stealskilllist"}, requiredType = Tester)
    public static class StealSkillList extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Set<Skill> skillSet = new HashSet<>();

            //Warriors
            int[] skillIds = new int[]{
                    //Hero
                    1101006, //Rage
                    1101011, //Brandish
                    1101012, //Combo Fury
                    1101013, //Combo Attack

                    1111014, //Shout
                    1111012, //Rush
                    1111010, //Intrepid Slash
                    1111008, //Shout

                    1121008, //Raging Blow
                    1121016, //Magic Crash(Hero)

                    1121054, //Cry Valhalla

                    //Paladin
                    1201011, //Flame Charge
                    1201012, //Blizzard Charge
                    1201013, //Close Combat

                    1211013, //Threaten
                    1211014, //Parashock Guard
                    1211012, //Rush
                    1211011, //Combat order
                    1211010, //HP Recovery
                    1211008, //Lightning Charge

                    1221016, //Guardian
                    1221014, //Magic Crash(Paladin)
                    1221011, //Heaven's Hammer
                    1221009, //Blast

                    1221054, //Sacrosanctity

                    //Dark Knight
                    1301007, //Hyper body
                    1301006, //Iron will
                    1301012, //Spear Sweep
                    1301013, //Evil Eye

                    1311015, //Cross Surge
                    1311011, //La Mancha Spear,
                    1311012, //Rush

                    1321012, //Dark Impale
                    1321013, //Gungnir's Descent
                    1321014, //Magic Crash(Dark Knight)

                    1321054, //Dark Thirst


                    2001002, //Magic Guard
                    //Mage FP
                    2101010, //Ignite
                    2101005, //Poison breath
                    2101004, //Flame Orb
                    2101001, //Meditation(FP)

                    2111002, //Explosion
                    2111003, //Poison mist

                    2121011, //Flame Haze
                    2121007, //Meteor Shower
                    2121006, //Paralyze

                    2121054, //Inferno Aura

                    //MageIL
                    2201008, //Cold Beam
                    2201005, //Thunder Bolt
                    2201001, //Meditation(IL)

                    2211010, //Glacier Chain

                    2221007, //Blizzard
                    2221012, //Frozen Orb
                    2221006, //Chain Lightning

                    2221054, //Absolute Zero Aura

                    //Bishop
                    2301004, //Bless
                    2301005, //Holy Arrow
                    2301002, //Heal

                    2311001, //Dispel
                    2311003, //Holy Symbol
                    2311004, //Shining Ray
                    2311011, //Holy Fountain
                    2311009, //Holy Magic Shell

                    2321008, //Genesis
                    2321007, //Angel Ray
                    2321006, //Resurrection
                    2321005, //Adv Blessing

                    2321054, //Righteously Indignant


                    //Bowmaster
                    3101008, //Covering Fire
                    3101005, //Arrowbomb

                    3111011, //Reckless Hunt: Bow
                    3111010, //Hookshot
                    3111003, //Flame Surge
                    3111013, //Arrow Blaster

                    3121004, //Hurricane
                    3121015, //Arrow Stream
                    3121002, //Sharp Eyes
                    3121014, //Blinding Shot

                    3121054, //Concentration

                    //Marksman
                    3201008, //Net Toss

                    3211008, //Dragon Breath
                    3211009, //Explosive Bolt
                    3211010, //Hookshot
                    3211011, //Pain Killer
                    3211012, //Reckless Hunt: XBow

                    3221007, //Snipe
                    3221006, //Illusion Step
                    3221002, //Sharp Eyes
                    3221001, //Piercing Arrow

                    3221054, //BullsEye Shot


                    4001003, //Dark Sight
                    4001005, //Haste
                    //Night Lord
                    4101011, //Sin Mark
                    4101010, //Gust Charm
                    4101008, //Shuriken Burst

                    4111013, //Shade Splitter
                    4111015, //Shade Splitter
                    4111010, //Triple Throw
                    4111003, //Shadow Web

                    4121017, //Showdown
                    4121016, //Sudden Raid (NL)
                    4121015, //Frailty Curse
                    4121013, //Quad Star

                    4121054, //Bleed Dart

                    //Shadower
                    4201012, //Svg Blow
                    4201011, //Meso Guard
                    4201004, //Steal

                    4211011, //Midnight Carnival
                    4211006, //Meso Explosion
                    4211002, //Phase Dash

                    4221014, //Assassinate
                    4221010, //Sudden Raid(Shad)
                    4221007, //Bstep
                    4221006, //Smoke screen

                    4221054, //Flip of the Coin

                    //Dual Blade
                    4301003, //Self Haste

                    4311003, //Slash Storm
                    4311002, //Fatal Blow

                    4321006, //Flying Assaulter
                    4321004, //Upper Stab
                    4321002, //FlashBang

                    4331011, //Blade Ascension
                    4331006, //Chains of Hell

                    4341011, //Sudden Raid (DB)
                    4341009, //Phantom Blow
                    4341004, //Blade Fury
                    4341002, //Final Cut

                    4341054, //Blade Clone


                    5001005, //Dash
                    //Bucc
                    5101004, //Corkscrew Blow

                    5111007, //Roll of the Dice
                    5111006, //Shock wave
                    5111009, //Spiral Assault
                    5111015, //Static Thumper
                    5111012, //Static Thumper

                    5121013, //Nautilus Strike
                    5121010, //Time Leap
                    5121009, //Speed Infusion
                    5121020, //octopunch
                    5121015, //Crossbones

                    5121054, //Stimulating Conversation

                    //Corsair
                    5201012, //Scurvy Summons
                    5201011, //Wings
                    5201006, //Recoil Shot
                    5201001, //Rapid blast

                    5211007, //Roll of the Dice
                    5211011, //All Aboard
                    5211009, //Cross cut Blast
                    5211010, //Blackboot bill
                    5211014, //Octo Cannon

                    5221018, //Jolly Roger
                    5221015, //Parrotargetting
                    5221016, //Brain scrambler
                    5221013, //Nautilus Strike
                    5221017, //Eigh-legs Easton
                    5221022, //Broadside

                    5221054, //Whaler's potion

                    //Cannon Master
                    5011001, //Cannon Strike

                    5301003, //Monkey Magic
                    5301001, //Barrel Bomb
                    5301000, //Scatter Shot

                    5311004, //Barrel Roulette
                    5311003, //Cannon Jump
                    5311005, //Luck of the Die
                    5311010, //Monkey Fury
                    5311002, //Monkey Wave
                    5311000, //Cannon Spike

                    5321012, //Cannon Barrage
                    5321010, //Pirate Spirit
                    5321004, //Monkey Militia
                    5321003, //Anchor Aweigh
                    5321001, //Nautilus Strike
                    5321000, //Cannon Bazooka

                    5321054, //BuckShot
            };

            for (int skillId : skillIds) {
                Skill skill = SkillData.getSkillDeepCopyById(skillId);
                if (skill == null) {
                    continue;
                }
                skillSet.add(skill);
            }

            chr.write(UserLocal.resultStealSkillList(skillSet, 4, 1, 2412));
        }
    }

    @Command(names = {"np", "nearestportal"}, requiredType = Tester)
    public static class NearestPortal extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            Rect rect = new Rect(
                    new Position(
                            chr.getPosition().deepCopy().getX() - 30,
                            chr.getPosition().deepCopy().getY() - 30),
                    new Position(
                            chr.getPosition().deepCopy().getX() + 30,
                            chr.getPosition().deepCopy().getY() + 30)
            );
            chr.chatMessage(Normal, "~~~~~~~~~~");
            chr.chatMessage(SpeakerChannel, "Current Map: " + NumberFormat.getNumberInstance(Locale.US).format(chr.getFieldID()));
            chr.chatMessage(SpeakerChannel, "Current ReturnMap: " + NumberFormat.getNumberInstance(Locale.US).format(chr.getField().getReturnMap()));
            chr.chatMessage(SpeakerChannel, "");
            for (Portal portal : chr.getField().getClosestPortal(rect)) {
                chr.chatMessage(SpeakerChannel, "Portal Name: " + portal.getName());
                chr.chatMessage(SpeakerChannel, "Portal ID: " + NumberFormat.getNumberInstance(Locale.US).format(portal.getId()));
                chr.chatMessage(SpeakerChannel, "Portal target map: " + NumberFormat.getNumberInstance(Locale.US).format(portal.getTargetMapId()));
                chr.chatMessage(SpeakerChannel, "Portal script: " + portal.getScript());
                chr.chatMessage(SpeakerChannel, ".");
            }
            chr.chatMessage(Normal, "~~~~~~~~~~");
        }
    }

    @Command(names = {"stats"}, requiredType = Tester)
    public static class Stats extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int strength = chr.getStat(Stat.str);
            int dexterity = chr.getStat(Stat.dex);
            int intellect = chr.getStat(Stat.inte);
            int luck = chr.getStat(Stat.luk);
            int hp = chr.getStat(Stat.hp);
            int mhp = chr.getStat(Stat.mhp);
            int mp = chr.getStat(Stat.mp);
            int mmp = chr.getStat(Stat.mmp);
            double hpratio = (((double) hp) / mhp) * 100;
            double mpratio = (((double) mp) / mmp) * 100;
            DecimalFormat formatNumbers = new DecimalFormat("##.00");
            NumberFormat addDeci = NumberFormat.getNumberInstance(Locale.US);
            chr.chatMessage(Notice2, "STR: " + addDeci.format(strength) + "  DEX: " + addDeci.format(dexterity) + "  INT: " + addDeci.format(intellect) + "  LUK: " + addDeci.format(luck));
            chr.chatMessage(Notice2, "HP: " + addDeci.format(hp) + " / " + addDeci.format(mhp) + " (" + formatNumbers.format(hpratio) + "%)   MP: " + addDeci.format(mp) + " / " + addDeci.format(mmp) + " (" + formatNumbers.format(mpratio) + "%)");
        }
    }

    @Command(names = {"spawn"}, requiredType = Tester)
    public static class Spawn extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage("Usage: !spawn <id> (<amount> <hp>).");
                return;
            }
            int id = Integer.parseInt(args[1]);
            int count = 1;
            if (args.length > 2) {
                count = Integer.parseInt(args[2]);
                if (count > 100) {
                    count = 100;
                    chr.chatMessage("You put the amount of mobs to spawn above 100, capping it to 100.");
                }
            }
            long hp = 0;
            if (args.length > 3) {
                hp = Long.parseLong(args[3]);
            }
            for (int i = 0; i < count; i++) {
                Mob mob = MobData.getMobDeepCopyById(id);
                if (mob == null) {
                    chr.chatMessage("Could not find a mob with that ID.");
                    return;
                }
                Field field = chr.getField();
                Position pos = chr.getPosition();
                Foothold fh = field.getFootholdById(chr.getFoothold());
                mob.setCurFoodhold(fh);
                mob.setHomeFoothold(fh);
                mob.setPosition(pos.deepCopy());
                mob.setPrevPos(pos.deepCopy());
                mob.setPosition(pos.deepCopy());
                if (hp > 0) {
                    mob.setMaxHp(hp);
                    mob.setHp(hp);
                }
                mob.setNotRespawnable(true);
                if (mob.getField() == null) {
                    mob.setField(field);
                }
                field.spawnLife(mob, null);
            }
        }
    }

    @Command(names = {"npc", "spawnnpc"}, requiredType = GameMaster)
    public static class NPC extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            Npc npc = NpcData.getNpcDeepCopyById(id);
            if (npc == null) {
                chr.chatMessage("Could not find an npc with that ID.");
                return;
            }
            Field field = chr.getField();
            Position pos = chr.getPosition();
            npc.setPosition(pos.deepCopy());
            npc.setCy(chr.getPosition().getY());
            npc.setRx0(chr.getPosition().getX() + 50);
            npc.setRx1(chr.getPosition().getX() - 50);
            npc.setFh(chr.getFoothold());
            npc.setNotRespawnable(true);
            if (npc.getField() == null) {
                npc.setField(field);
            }
            field.spawnLife(npc, null);
            log.debug("npc has id " + npc.getObjectId());
        }
    }

    @Command(names = {"drop"}, requiredType = Tester)
    public static class DropItem extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            final int itemId = Integer.parseInt(args[1]);
            chr.dropItem(itemId, chr.getPosition().getX(), chr.getPosition().getY());
        }
    }

    @Command(names = {"testdrop", "droptest"}, requiredType = Tester)
    public static class TestDrop extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int id = Integer.parseInt(args[1]);
            int count = 1;
            if (args.length > 2) {
                count = Integer.parseInt(args[2]);
            }
            for (int i = 0; i < count; i++) {
                Mob mob = MobData.getMobDeepCopyById(id);
                if (mob == null) {
                    chr.chatMessage("Could not find a mob with that ID.");
                    return;
                }
                Field field = chr.getField();
                Position pos = chr.getPosition();
                mob.setPosition(pos.deepCopy());
                mob.setPrevPos(pos.deepCopy());
                mob.setPosition(pos.deepCopy());
                mob.getForcedMobStat().setMaxMP(3);
                mob.setMaxHp(3);
                mob.setHp(3);
                mob.setNotRespawnable(true);
                if (mob.getField() == null) {
                    mob.setField(field);
                }
                mob.addDamage(chr, 1); // for drop/meso%
                mob.dropDrops();
            }
        }
    }

    @Command(names = {"proitem"}, requiredType = Tester)
    public static class ProItem extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 5) {
                chr.chatMessage(Notice2, "Needs more args! <id> <Stat> <Attack> <Flame stats>");
                return;
            }
            int id = Integer.parseInt(args[1]);
            int stat = Integer.parseInt(args[2]);
            int atk = Integer.parseInt(args[3]);
            int flames = Integer.parseInt(args[4]);
            Equip equip = ItemData.getEquipDeepCopyFromID(id, false, chr.getJob());
            equip.setBaseStat(EquipBaseStat.iStr, stat);
            equip.setBaseStat(EquipBaseStat.iDex, stat);
            equip.setBaseStat(EquipBaseStat.iInt, stat);
            equip.setBaseStat(EquipBaseStat.iLuk, stat);
            equip.setBaseStat(EquipBaseStat.iMaxHP, stat);
            equip.setBaseStat(EquipBaseStat.iMaxMP, stat);
            equip.setBaseStat(EquipBaseStat.iDEF, stat);
            equip.setBaseStat(EquipBaseStat.iPAD, atk);
            equip.setBaseStat(EquipBaseStat.iMAD, atk);
            equip.setBaseStat(EquipBaseStat.bdr, flames);
            equip.setBaseStat(EquipBaseStat.imdr, flames);
            equip.setBaseStat(EquipBaseStat.damR, flames);
            equip.setBaseStat(EquipBaseStat.statR, flames);

            chr.addItemToInventory(InvType.EQUIP, equip, false);
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    Add, (short) equip.getBagIndex(), (byte) 1,
                    0, equip));

        }
    }

    @Command(names = {"hide"}, requiredType = Tester)
    public static class Hide extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.setHide(args.length < 2 ? !chr.isHide()
                    : Util.isNumber(args[1]) ? Integer.parseInt(args[1]) != 0 : Boolean.parseBoolean(args[1]));
        }
    }

    @Command(names = {"setpotential", "setpot"}, requiredType = Tester)
    public static class SetPotential extends AdminCommand {

        public static void execute(Char chr, String[] args) {

            if (args.length < 5) {
                chr.chatMessage("Usage: !setpot <inv position> <id 1st line> <id 2nd line> <id 3rd line>");
                return;
            }
            int invPosition = Integer.parseInt(args[1]);
            Equip equip = (Equip) chr.getInventoryByType(InvType.EQUIP).getItemBySlot(invPosition);
            if (equip == null) {
                chr.chatMessage("There is no equip on this position.");
                return;
            }
            equip.setOptionBase(0, Integer.parseInt(args[2]));
            equip.setOptionBase(1, Integer.parseInt(args[3]));
            equip.setOptionBase(2, Integer.parseInt(args[4]));
            equip.updateToChar(chr);
        }
    }

    @Command(names = {"setbonuspotential", "setbpotential", "setbpot"}, requiredType = Tester)
    public static class SetBonusPotential extends AdminCommand {

        public static void execute(Char chr, String[] args) {

            if (args.length < 5) {
                chr.chatMessage("Usage: !setbpot <inv position> <id 1st line> <id 2nd line> <id 3rd line>");
                return;
            }
            int invPosition = Integer.parseInt(args[1]);
            Equip equip = (Equip) chr.getInventoryByType(InvType.EQUIP).getItemBySlot(invPosition);
            if (equip == null) {
                chr.chatMessage("There is no equip on this position.");
                return;
            }
            equip.setOptionBonus(0, Integer.parseInt(args[2]));
            equip.setOptionBonus(1, Integer.parseInt(args[3]));
            equip.setOptionBonus(2, Integer.parseInt(args[4]));
            equip.updateToChar(chr);
        }
    }

    @Command(names = {"setflames", "flames"}, requiredType = Tester)
    public static class SetFlames extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 7) {
                chr.chatMessage("Usage: !flames <inv position> <stats> <att> <as%> <td%> <bd%>");
                return;
            }
            int invPosition = Integer.parseInt(args[1]);
            Equip equip = (Equip) chr.getInventoryByType(InvType.EQUIP).getItemBySlot(invPosition);
            if (equip == null) {
                chr.chatMessage("There is no equip on this position.");
                return;
            }
            int stat = Integer.parseInt(args[2]);
            int att = Integer.parseInt(args[3]);
            int as = Integer.parseInt(args[4]);
            int td = Integer.parseInt(args[5]);
            int bd = Integer.parseInt(args[6]);

            equip.setfSTR(stat);
            equip.setfDEX(stat);
            equip.setfINT(stat);
            equip.setfLUK(stat);
            equip.setfHP(stat);
            equip.setfMP(stat);
            equip.setfDEF(stat);
            equip.setfSpeed(stat);
            equip.setfJump(stat);

            equip.setfATT(att);
            equip.setfMATT(att);

            equip.setfAllStat(as);
            equip.setfDamage(td);
            equip.setfBoss(bd);
            equip.updateToChar(chr);
        }
    }

    @Command(names = {"setflame", "flame"}, requiredType = Tester)
    public static class SetFlame extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 4) {
                chr.chatMessage("Usage: !flame <inv position> <flame type> <value>");
                chr.chatMessage("Example: !flame 1 str 40");
                return;
            }
            int invPosition = Integer.parseInt(args[1]);
            Equip equip = (Equip) chr.getInventoryByType(InvType.EQUIP).getItemBySlot(invPosition);
            if (equip == null) {
                chr.chatMessage("There is no equip on this position.");
                return;
            }
            String flame = args[2].toLowerCase();
            short value = Short.parseShort(args[3]);
            switch (flame) {
                case "str":
                    equip.setfSTR(value);
                    break;
                case "dex":
                    equip.setfDEX(value);
                    break;
                case "int":
                    equip.setfINT(value);
                    break;
                case "luk":
                    equip.setfLUK(value);
                    break;
                case "att":
                case "atk":
                    equip.setfATT(value);
                    break;
                case "matt":
                case "matk":
                    equip.setfMATT(value);
                    break;
                case "def":
                    equip.setfDEF(value);
                    break;
                case "hp":
                    equip.setfHP(value);
                    break;
                case "mp":
                    equip.setfMP(value);
                    break;
                case "speed":
                    equip.setfSpeed(value);
                    break;
                case "jump":
                    equip.setfJump(value);
                    break;
                case "allstat":
                case "as":
                    equip.setfAllStat(value);
                    break;
                case "boss":
                case "bdmg":
                case "bdr":
                    equip.setfBoss(value);
                    break;
                case "dmg":
                case "damage":
                    equip.setfDamage(value);
                    break;
                case "level":
                case "lvl":
                case "lv":
                    equip.setfLevel(value);
                    break;
                case "reset":
                    equip.resetFlameStats();
                    break;
                default:
                    chr.chatMessage("Unknown Flame Type");
                    return;
            }
            equip.updateToChar(chr);
        }
    }

    @Command(names = {"item"}, requiredType = Tester)
    public static class GetItem extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (Util.isNumber(args[1])) {

                int id = Integer.parseInt(args[1]);
                Equip equip = ItemData.getEquipDeepCopyFromID(id, true);
                if (equip == null) {
                    Item item = ItemData.getItemDeepCopy(id, true);
                    if (item == null) {
                        chr.chatMessage(WorldName, String.format("Could not find an item with id %d", id));
                        return;
                    }
                    short quant = 1;
                    if (args.length > 2) {
                        quant = Short.parseShort(args[2]);
                    }
                    item.setQuantity(quant);
                    chr.addItemToInventory(item);
                } else {
                    chr.addItemToInventory(InvType.EQUIP, equip, false);
                }
            } else {
                StringBuilder query = new StringBuilder();
                int size = args.length;
                short quant = 1;
                if (Util.isNumber(args[size - 1])) {
                    size--;
                    quant = Short.parseShort(args[size]);
                }
                for (int i = 1; i < size; i++) {
                    query.append(args[i].toLowerCase()).append(" ");
                }
                query = new StringBuilder(query.substring(0, query.length() - 1));
                Map<Integer, String> map = StringData.getItemStringByName(query.toString());
                if (map.size() == 0) {
                    chr.chatMessage(WorldName, "No items found for query " + query);
                }
                for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    int id = entry.getKey();
                    Item item = ItemData.getEquipDeepCopyFromID(id, true);
                    if (item != null) {
                        Equip equip = (Equip) item;
                        if (equip.getItemId() < 1000000) {
                            continue;
                        }
                        chr.addItemToInventory(equip);
                        chr.getClient().write(WvsContext.inventoryOperation(true, false,
                                Add, (short) equip.getBagIndex(), (byte) -1, 0, equip));
                        return;
                    }
                    item = ItemData.getItemDeepCopy(id);
                    if (item == null) {
                        continue;
                    }
                    item.setQuantity(quant);
                    chr.addItemToInventory(item);
                    chr.getClient().write(WvsContext.inventoryOperation(true, false,
                            Add, (short) item.getBagIndex(), (byte) -1, 0, item));
                    return;
                }
            }
        }
    }

    @Command(names = {"done"}, requiredType = Tester)
    public static class Done extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int num = 1000;
            int hp = 250000;
            int lv = 235;
            chr.setStatAndSendPacket(Stat.hp, hp);
            chr.setStatAndSendPacket(Stat.mhp, hp);
            chr.setStatAndSendPacket(Stat.mp, hp);
            chr.setStatAndSendPacket(Stat.mmp, hp);
            chr.setStatAndSendPacket(Stat.str, (short) num);
            chr.setStatAndSendPacket(Stat.dex, (short) num);
            chr.setStatAndSendPacket(Stat.inte, (short) num);
            chr.setStatAndSendPacket(Stat.luk, (short) num);
            chr.setStatAndSendPacket(Stat.level, lv);
        }
    }

    @Command(names = {"hypertp"}, requiredType = Tester)
    public static class HyperTP extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int hyperTP = 5040004;
            Item hyperTP2 = ItemData.getItemDeepCopy(hyperTP);
            chr.addItemToInventory(hyperTP2.getInvType(), hyperTP2, false);
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    Add, (short) hyperTP2.getBagIndex(), (byte) -1, 0, hyperTP2));
        }
    }

    @Command(names = {"job", "setjob"}, requiredType = Tester)
    public static class Job extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            short id = Short.parseShort(args[1]);
            JobEnum job = JobEnum.getJobById(id);
            if (job != null) {
                chr.setJob(id);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.job, id);
                chr.getClient().write(WvsContext.statChanged(stats));
            } else {
                chr.chatMessage("Unknown job id " + id);
            }
        }
    }

    @Command(names = {"sp", "setsp"}, requiredType = Tester)
    public static class Sp extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setSpToCurrentJob(num);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
                chr.getClient().write(WvsContext.statChanged(stats));
            }
        }
    }

    @Command(names = {"ap", "setap"}, requiredType = Tester)
    public static class Ap extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStatAndSendPacket(Stat.ap, (short) num);
            }
        }
    }

    /**
     * Sets the max HP and heals.
     */
    @Command(names = {"maxhp", "setmaxhp"}, requiredType = Tester)
    public static class Hp extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStatAndSendPacket(Stat.hp, num);
                chr.setStatAndSendPacket(Stat.mhp, num);
            }
        }
    }

    /**
     * Sets the max MP and heals.
     */
    @Command(names = {"maxmp", "setmaxmp"}, requiredType = Tester)
    public static class Mp extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStatAndSendPacket(Stat.mp, num);
                chr.setStatAndSendPacket(Stat.mmp, num);
            }
        }
    }

    /**
     * Sets the max HP and heals.
     */
    @Command(names = {"currenthp", "setcurrenthp"}, requiredType = Tester)
    public static class SetCurrentHp extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStatAndSendPacket(Stat.hp, num);
            }
        }
    }

    /**
     * Sets the max MP and heals.
     */
    @Command(names = {"currentmp", "setcurrentmp"}, requiredType = Tester)
    public static class SetCurrentMp extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStatAndSendPacket(Stat.mp, num);
            }
        }
    }

    @Command(names = {"setstat"}, requiredType = Tester)
    public static class SetStat extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length == 3) {
                String statName = args[1].toLowerCase();
                int amount = Integer.parseInt(args[2]);
                if (statName.equalsIgnoreCase("str")
                        || statName.equalsIgnoreCase("dex")
                        || statName.equalsIgnoreCase("inte")
                        || statName.equalsIgnoreCase("luk")) {
                    Stat stat = Stat.valueOf(statName);
                    chr.setStatAndSendPacket(stat, (short) amount);
                    return;
                }
            }
            chr.chatMessage("setstat <str, dex, inte, luk> <amount>");
        }
    }

    @Command(names = {"level", "setlevel", "lvl", "lv"}, requiredType = Tester)
    public static class Level extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            if (num >= 0) {
                chr.setStatAndSendPacket(Stat.level, num);
                chr.setStatAndSendPacket(Stat.exp, 0);
                chr.getJobHandler().handleLevelUp();
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.levelUpEffect()));
            }
        }
    }

    @Command(names = {"leveluntil", "levelupuntil", "leveltill", "leveluptill"}, requiredType = Tester)
    public static class LevelUntil extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int num = Integer.parseInt(args[1]);
            int level = chr.getLevel();
            if (num > 275) {
                chr.chatMessage("You can't be above lvl 275");
                chr.dispose();
                return;
            }
            while (level < num) {
                level++;
                chr.setStat(Stat.level, level);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.level, level);
                stats.put(Stat.exp, (long) 0);
                chr.getClient().write(WvsContext.statChanged(stats));
                chr.getJobHandler().handleLevelUp();
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.levelUpEffect()));
            }
        }
    }


    @Command(names = {"heal"}, requiredType = Tester)
    public static class Heal extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int hp = chr.getMaxHP();
            if (args.length > 1) {
                hp = Integer.parseInt(args[1]);
            }
            chr.heal(hp);
            chr.healMP(chr.getMaxMP());
        }
    }

    @Command(names = {"gethp"}, requiredType = Tester)
    public static class CurrentHp extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            String result = String.format("CurrentHP: %d, MaxHP: %d", chr.getHP(), chr.getMaxHP());
            chr.chatMessage(result);
        }
    }

    @Command(names = {"getmp"}, requiredType = Tester)
    public static class CurrentMp extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            String result = String.format("CurrentMP: %d, MaxMP: %d", chr.getMP(), chr.getMaxMP());
            chr.chatMessage(result);
        }
    }

    @Command(names = {"invincible", "god", "godmode"}, description = "Toggles invincibility.", requiredType = Tester)
    public static class Godmode extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            chr.setInvincible(!chr.isInvincible());
            chr.chatMessage("Invincibility: " + chr.isInvincible());
            if (chr.isInvincible()) {
                Option o = new Option();
                o.nOption = 3;
                tsm.putCharacterStatValue(NotDamaged, o);
            } else {
                tsm.removeStat(NotDamaged, true);
            }
            tsm.sendSetStatPacket();
        }
    }

    @Command(names = {"morph"}, requiredType = Tester)
    public static class Morph extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int morphID = Integer.parseInt(args[1]);
            if (args.length < 2) {
                chr.chatMessage(Notice2, "Needs more args! <id>");
            }
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o1 = new Option();
            o1.nOption = morphID;
            o1.rOption = Kaiser.FINAL_TRANCE;
            tsm.putCharacterStatValue(CharacterTemporaryStat.Morph, o1);
            tsm.sendSetStatPacket();
        }
    }

    @Command(names = {"mount"}, requiredType = Tester)
    public static class Mount extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int mountID = Integer.parseInt(args[1]);
            if (args.length < 2) {
                chr.chatMessage(Notice2, "Needs more args! <id>");
            }
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);
            tsb.setNOption(mountID);
            tsb.setROption(Kaiser.FINAL_TRANCE);
            tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
            tsm.sendSetStatPacket();
        }
    }

    @Command(names = {"testtempstat"}, requiredType = Admin)
    public static class TestTempStat extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            List<Life> lifes = new ArrayList<>(chr.getField().getLifes().values());
            Life l = lifes.get(lifes.size() - 1);
            if (!(l instanceof Mob)) {
                return;
            }
            Mob mob = (Mob) l;
            chr.getClient().write(MobPool.statSet(mob, (short) 0));
        }
    }

    @Command(names = {"map", "warp"}, requiredType = Tester)
    public static class SetMap extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length > 1 && Util.isNumber(args[1])) {
                Field toField = chr.getOrCreateFieldByCurrentInstanceType(Integer.parseInt(args[1]));
                if (toField != null) {
                    chr.warp(toField);
                } else {
                    chr.chatMessage(Notice2, "Could not find a field with id " + args[1]);
                }
            } else {
                chr.chatMessage("Please input a number as first argument.");
            }
        }
    }

    @Command(names = {"setportal"}, requiredType = Tester)
    public static class SetPortal extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int portalID = Integer.parseInt(args[1]);
            Portal portal = chr.getField().getPortalByID(portalID);
            if (portal == null) {
                chr.chatMessage(Notice2, "Portal does not exist.");
                return;
            }
            Position position = new Position(portal.getX(), portal.getY());
            chr.write(FieldPacket.teleport(position, chr));
        }
    }

    @Command(names = {"atom"}, requiredType = Admin)
    public static class Atom extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int charID = chr.getId();
            ForceAtomInfo forceAtomInfo1 = new ForceAtomInfo(142110011, ForceAtomEnum.KINESIS_ORB_REAL.getInc(), 3, 3, 0, 0, Util.getCurrentTime(), 1,
                    142110011, new Position());
            ForceAtomInfo forceAtomInfo2 = new ForceAtomInfo(142110011, ForceAtomEnum.KINESIS_ORB_REAL.getInc(), 3, 3, 0, 0, Util.getCurrentTime(), 1,
                    142110011, new Position());
            List<ForceAtomInfo> fais = new ArrayList<>();
            fais.add(forceAtomInfo1);
            fais.add(forceAtomInfo2);

            Mob mob = (Mob) chr.getField().getLifes().get(chr.getField().getLifes().size() - 1);
            List<Integer> mobs = new ArrayList<>();
            int mobID = mob.getObjectId();
            mobs.add(mobID);
            chr.createForceAtom(new ForceAtom(false, -1, chr.getId(), ForceAtomEnum.KINESIS_ORB_REAL,
                    true, mobs, 142110011, fais, null, 0, 0, null, 142110011, mob.getPosition(), 0));

        }
    }

    @Command(names = {"getskill"}, requiredType = Tester)
    public static class GetSkill extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 4) {
                chr.chatMessage(Notice2, "Needs more args! <id> <cur> <max>");
                return;
            }
            int id = Integer.parseInt(args[1]);
            int cur = Integer.parseInt(args[2]);
            int max = Integer.parseInt(args[3]);
            chr.addSkill(id, cur, max);
        }
    }

    @Command(names = {"maxskills"}, requiredType = Tester)
    public static class MaxSkills extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            List<Skill> list = new ArrayList<>();
            Set<Short> jobs = new HashSet<>();
            short job = chr.getJob();
            // giant hack, but it's for a command, so it's k
            if (JobConstants.isEvan(job)) {
                jobs.add((short) 2000);
                jobs.add((short) 2200);
                while (job >= 2210) {
                    jobs.add(job--);
                }
            } else {
                if (job % 100 == 12) {
                    jobs.add(job);
                    jobs.add((short) (job - 1));
                    jobs.add((short) (job - 2));
                    jobs.add((short) (job - 3));
                    jobs.add((short) (job - 4));
                    jobs.add((short) (job - 5));
                    jobs.add((short) (job - 6));
                    jobs.add((short) (job - 7));
                    jobs.add((short) (job - 8));
                    jobs.add((short) (job - 9));
                    jobs.add((short) (job - 10));
                    jobs.add((short) (job - 11));
                    jobs.add((short) (job - 12));
                } else if (job % 100 == 11) {
                    jobs.add(job);
                    jobs.add((short) (job - 1));
                    jobs.add((short) (job - 2));
                    jobs.add((short) (job - 3));
                    jobs.add((short) (job - 4));
                    jobs.add((short) (job - 5));
                    jobs.add((short) (job - 6));
                    jobs.add((short) (job - 7));
                    jobs.add((short) (job - 8));
                    jobs.add((short) (job - 9));
                    jobs.add((short) (job - 10));
                    jobs.add((short) (job - 11));
                    jobs.add((short) (job - 12));
                } else if (job % 100 == 10) {
                    jobs.add(job);
                    jobs.add((short) (job - 1));
                    jobs.add((short) (job - 2));
                    jobs.add((short) (job - 3));
                    jobs.add((short) (job - 4));
                    jobs.add((short) (job - 5));
                    jobs.add((short) (job - 6));
                    jobs.add((short) (job - 7));
                    jobs.add((short) (job - 8));
                    jobs.add((short) (job - 9));
                    jobs.add((short) (job - 10));
                    jobs.add((short) (job - 11));
                    jobs.add((short) (job - 12));
                } else {
                    jobs.add(job);
                }
            }
            for (short j : jobs) {
                for (Skill skill : SkillData.getSkillsByJob(j)) {
                    byte maxLevel = (byte) skill.getMaxLevel();
                    skill.setCurrentLevel(maxLevel);
                    skill.setMasterLevel(maxLevel);
                    list.add(skill);
                    chr.addSkill(skill);
                }
                if (list.size() > 0) {
                    chr.getClient().write(WvsContext.changeSkillRecordResult(list, true, false, false, false));
                }
            }
        }
    }

    @Command(names = {"max"}, requiredType = Tester)
    public static class maxskills2 extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            List<Skill> list = new ArrayList<>();
            for (Skill skill : SkillData.getSkillsByJob(chr.getJob())) {
                byte maxLevel = (byte) skill.getMaxLevel();
                skill.setCurrentLevel(maxLevel);
                skill.setMasterLevel(maxLevel);
                list.add(skill);
                chr.addSkill(skill);
                chr.getClient().write(WvsContext.changeSkillRecordResult(list, true, false, false, false));
            }
        }
    }

    @Command(names = {"lookup", "find", "search"}, requiredType = Tester)
    public static class Lookup extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 3) {
                chr.chatMessage(Notice2, "Needs more args! <what to lookup> <id/(part of) name>");
                chr.chatMessage(Notice2, "Possible lookup types are: item, skill, mob, npc, map, quest");
                return;
            }
            StringBuilder query = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                query.append(args[i].toLowerCase()).append(" ");
            }
            query = new StringBuilder(query.substring(0, query.length() - 1));
            chr.chatMessage("Query: " + query);
            boolean isNumber = Util.isNumber(query.toString());
            if ("skill".equalsIgnoreCase(args[1])) {
                SkillStringInfo ssi;
                int id;
                if (isNumber) {
                    id = Integer.parseInt(query.toString());
                    ssi = StringData.getSkillStringById(id);
                    if (ssi == null) {
                        chr.chatMessage(WorldName, "Cannot find skill " + id);
                        return;
                    }
                    SkillInfo skillInfo = SkillData.getSkillInfoById(id);
                    chr.chatMessage(WorldName, "Name: " + ssi.getName());
                    chr.chatMessage(WorldName, "Desc: " + ssi.getDesc());
                    chr.chatMessage(WorldName, "h: " + ssi.getH());
                    chr.chatMessage(WorldName, "type: " + skillInfo.getType());
                } else {
                    Map<Integer, SkillStringInfo> map = StringData.getSkillStringByName(query.toString());
                    if (map.size() == 0) {
                        chr.chatMessage(WorldName, "No skills found for query " + query);
                    }
                    for (Map.Entry<Integer, SkillStringInfo> entry : map.entrySet()) {
                        id = entry.getKey();
                        ssi = entry.getValue();
                        SkillInfo si = SkillData.getSkillInfoById(id);
                        if (si != null) {
                            chr.chatMessage(WorldName, "Id: " + id);
                            chr.chatMessage(WorldName, "Name: " + ssi.getName());
                            chr.chatMessage(WorldName, "Desc: " + ssi.getDesc());
                            chr.chatMessage(WorldName, "h: " + ssi.getH());
                            chr.chatMessage(WorldName, "type: " + si.getType());
                        }
                    }
                }
            } else if ("option".equalsIgnoreCase(args[1])) {
                List<ItemOption> ioList = ItemData.getItemOptionsByName(query.toString());
                for (ItemOption io : ioList) {
                    int id = io.getId();
                    int tier = id / 10000;  // 1 = Rare,  2 = Epic,  3 = Unique,  4 = Legendary
                    ChatType chatType;
                    String ioString = "";
                    if (tier > 0 && tier <= 4) {
                        boolean bonus = (id % (tier * 10000)) >= 2000;
                        if (bonus) {
                            ioString += "[Bonus] ";
                        }
                    }
                    switch (tier) {
                        case 1:
                            chatType = Notice2; // Rare
                            ioString += "(Rare) ";
                            break;
                        case 2:
                            chatType = GroupParty; // Epic
                            ioString += "(Epic) ";
                            break;
                        case 3:
                            chatType = Notice; // Unique
                            ioString += "(Unique) ";
                            break;
                        case 4:
                            chatType = Whisper; // Legendary
                            ioString += "(Legendary) ";
                            break;
                        default:
                            chatType = WorldName; // Other
                            ioString += "(Other) ";
                            break;
                    }
                    ioString += io.getString();
                    chr.chatMessage(chatType, "Id: " + id);
                    chr.chatMessage(chatType, "Name: " + ioString);
                }
            } else {
                String queryType = args[1].toLowerCase();
                int id;
                String name;
                if (isNumber) {
                    id = Integer.parseInt(query.toString());
                    switch (queryType) {
                        case "item":
                            name = StringData.getItemStringById(id);
                            break;
                        case "quest":
                            name = StringData.getQuestStringById(id);
                            break;
                        case "mob":
                            name = StringData.getMobStringById(id);
                            break;
                        case "npc":
                            name = StringData.getNpcStringById(id);
                            break;
                        case "map":
                            name = StringData.getMapStringById(id);
                            break;
                        default:
                            chr.chatMessage("Unknown query type " + queryType);
                            return;
                    }
                    if (name == null) {
                        chr.chatMessage(WorldName, "Cannot find " + queryType + " " + id);
                        return;
                    }
                    chr.chatMessage(WorldName, "Name: " + name);
                } else {
                    Map<Integer, String> map;
                    switch (queryType) {
                        case "equip":
                            map = StringData.getItemStringByName(query.toString());
                            Set<Integer> nonEquips = new HashSet<>();
                            for (int itemId : map.keySet()) {
                                if (!ItemConstants.isEquip(itemId)) {
                                    nonEquips.add(itemId);
                                }
                            }
                            for (int itemId : nonEquips) {
                                map.remove(itemId);
                            }
                            break;
                        case "item":
                            map = StringData.getItemStringByName(query.toString());
                            break;
                        case "quest":
                            map = StringData.getQuestStringByName(query.toString());
                            break;
                        case "mob":
                            map = StringData.getMobStringByName(query.toString());
                            break;
                        case "npc":
                            map = StringData.getNpcStringByName(query.toString());
                            break;
                        case "map":
                            map = StringData.getMapStringByName(query.toString());
                            break;
                        default:
                            chr.chatMessage("Unknown query type " + queryType);
                            return;
                    }
                    if (map.size() == 0) {
                        chr.chatMessage(WorldName, "No " + queryType + "s found for query " + query);
                        return;
                    }
                    TreeMap<Integer, String> sortedMap = new TreeMap<>(map);
                    for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
                        id = entry.getKey();
                        name = entry.getValue();
                        if (queryType.equalsIgnoreCase("item")) {
                            Item item = ItemData.getEquipDeepCopyFromID(id, false);
                            if (item == null) {
                                item = ItemData.getItemDeepCopy(id);
                            }
                            if (item == null) {
                                continue;
                            }
                        }
                        chr.chatMessage(WorldName, "Id: " + id);
                        chr.chatMessage(WorldName, "Name: " + name);
                    }
                }
            }
        }
    }

    @Command(names = {"getprojectiles", "projectiles"}, requiredType = Tester)
    public static class GetProjectiles extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] projectiles = new int[]{
                    2070000,
                    2060000,
                    2061000,
                    2330000
            };
            for (int projectile : projectiles) {
                Item item = ItemData.getItemDeepCopy(projectile);
                chr.addItemToInventory(item.getInvType(), item, false);
                item.setQuantity(1000);
                chr.getClient().write(WvsContext.inventoryOperation(true, false,
                        Add, (short) item.getBagIndex(), (byte) -1, 0, item));
            }
        }
    }

    @Command(names = {"mesos", "money"}, requiredType = Tester)
    public static class Mesos extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            long mesos = Long.parseLong(args[1]);
            chr.addMoney(mesos);
        }
    }

    @Command(names = {"nx", "setnx"}, requiredType = Tester)
    public static class NxCommand extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int nx = Integer.parseInt(args[1]);
            chr.addNx(nx);
        }
    }

    @Command(names = {"maplepoints", "setmaplepoints"}, requiredType = Tester)
    public static class maplepoints extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int nx = Integer.parseInt(args[1]);
            chr.getUser().addMaplePoints(nx);
        }
    }

    @Command(names = {"dp", "setdp"}, requiredType = Tester)
    public static class DpCommand extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int dp = Integer.parseInt(args[1]);
            User user = chr.getUser();
            user.setDonationPoints(dp);
            chr.chatMessage(WhiteOnGreen, "You now have " + dp + " Donation Points :)");
        }
    }

    @Command(names = {"vp", "setvp"}, requiredType = Tester)
    public static class VpCommand extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int vp = Integer.parseInt(args[1]);
            User user = chr.getUser();
            user.setVotePoints(vp);
        }
    }


    @Command(names = {"goto"}, requiredType = Tester)
    public static class GoTo extends AdminCommand {
        public static void execute(Char chr, String[] args) {

            HashMap<String, Integer> gotomaps = new HashMap<>();
            gotomaps.put("ardent", 910001000);
            gotomaps.put("ariant", 260000100);
            gotomaps.put("amherst", 1010000);
            gotomaps.put("amoria", 680000000);
            gotomaps.put("aqua", 860000000);
            gotomaps.put("aquaroad", 230000000);
            gotomaps.put("boatquay", 541000000);
            gotomaps.put("cwk", 610030000);
            gotomaps.put("edelstein", 310000000);
            gotomaps.put("ellin", 300000000);
            gotomaps.put("ellinia", 101000000);
            gotomaps.put("ellinel", 101071300);
            gotomaps.put("elluel", 101050000);
            gotomaps.put("elnath", 211000000);
            gotomaps.put("ereve", 130000000);
            gotomaps.put("florina", 120000300);
            gotomaps.put("fm", 910000000);
            gotomaps.put("future", 271000000);
            gotomaps.put("gmmap", 180000000);
            gotomaps.put("happy", 209000000);
            gotomaps.put("harbor", 104000000);
            gotomaps.put("henesys", 100000000);
            gotomaps.put("herbtown", 251000000);
            gotomaps.put("kampung", 551000000);
            gotomaps.put("kerning", 103000000);
            gotomaps.put("korean", 222000000);
            gotomaps.put("leafre", 240000000);
            gotomaps.put("ludi", 220000000);
            gotomaps.put("malaysia", 550000000);
            gotomaps.put("mulung", 250000000);
            gotomaps.put("nautilus", 120000000);
            gotomaps.put("nlc", 600000000);
            gotomaps.put("omega", 221000000);
            gotomaps.put("orbis", 200000000);
            gotomaps.put("pantheon", 400000000);
            gotomaps.put("pinkbean", 270050100);
            gotomaps.put("phantom", 610010000);
            gotomaps.put("perion", 102000000);
            gotomaps.put("rien", 140000000);
            gotomaps.put("showatown", 801000000);
            gotomaps.put("singapore", 540000000);
            gotomaps.put("sixpath", 104020000);
            gotomaps.put("sleepywood", 105000000);
            gotomaps.put("southperry", 2000000);
            gotomaps.put("tot", 270000000);
            gotomaps.put("twilight", 273000000);
            gotomaps.put("tynerum", 301000000);
            gotomaps.put("zipangu", 800000000);
            gotomaps.put("pianus", 230040420);
            gotomaps.put("horntail", 240060200);
            gotomaps.put("chorntail", 240060201);
            gotomaps.put("griffey", 240020101);
            gotomaps.put("manon", 240020401);
            gotomaps.put("zakum", 280030000);
            gotomaps.put("czakum", 280030001);
            gotomaps.put("pap", 220080001);
            gotomaps.put("oxquiz", 109020001);
            gotomaps.put("ola", 109030101);
            gotomaps.put("fitness", 109040000);
            gotomaps.put("snowball", 109060000);
            gotomaps.put("boss", 682020000);
            gotomaps.put("dojo", 925020001);
            gotomaps.put("pq", 910002000);
            gotomaps.put("h", 100000000);
            gotomaps.put("gollux", 863010000);
            gotomaps.put("lotus", 350060300);
            gotomaps.put("damien", 105300303);
            gotomaps.put("ursus", 970072200);
            gotomaps.put("pno", 811000008);
            gotomaps.put("cygnus", 271040000);
            gotomaps.put("ra", 105200000);
            gotomaps.put("goldenbeach", 914200000);
            gotomaps.put("ardentmill", 910001000);
            gotomaps.put("oz", 992000000);
            gotomaps.put("vj", 450001000);
            gotomaps.put("chu", 450002000);
            gotomaps.put("chuchu", 450002000);
            gotomaps.put("lach", 450003000);
            gotomaps.put("lachelein", 450003000);
            gotomaps.put("arcana", 450005000);
            gotomaps.put("morass", 450006130);
            gotomaps.put("esfera", 450007000);
            gotomaps.put("outpost", 450009000);
            gotomaps.put("moonbridge", 450009100);
            gotomaps.put("lab", 450011120);
            gotomaps.put("labyrinth", 450011120);
            gotomaps.put("limina", 450012000);
            gotomaps.put("runner", 993001000);

            if (args.length == 1) {
                chr.chatMessage(Notice2, "List of locations: " + gotomaps.keySet());
            } else if (gotomaps.containsKey(args[1])) {
                Field toField = chr.getClient().getChannelInstance().getField(gotomaps.get(args[1]));
                Portal portal = chr.getField().getDefaultPortal();
                chr.warp(toField, portal);
            } else if (args[1].equals("locations")) {
                chr.chatMessage(Notice2, "Use !goto <location>");
                StringBuilder sb = new StringBuilder();
                for (String s : gotomaps.keySet()) {
                    sb.append(s).append(",  ");
                }
                chr.chatMessage(Notice2, sb.substring(0, sb.length() - 2));
            } else {
                chr.chatMessage(Notice2, "Map does not exist.");
            }
        }
    }

    @Command(names = {"pos"}, requiredType = Tester)
    public static class ChrPosition extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.chatMessage("You are currently @ " + chr.getPosition());
        }
    }

    @Command(names = {"savemap"}, requiredType = Tester)
    public static class SaveMap extends AdminCommand {
        private static final HashMap<String, Integer> quickmaps = new HashMap<>();

        public static void execute(Char chr, String[] args) {
            int mapid = chr.getFieldID();
            if (args.length < 1 && !args[1].equalsIgnoreCase("list")) {
                chr.chatMessage(BlackOnWhite, "Incorrect Syntax: !SaveMap <save/go> <key>");
                chr.chatMessage(BlackOnWhite, "To see the list of saved maps, use: !SaveMap list");
            }
            if (args[1].equalsIgnoreCase("save")) {
                String key = args[2];
                quickmaps.put(key, mapid);
                chr.chatMessage(BlackOnWhite, "[SaveMap] Map: " + mapid + " has been saved as key '" + key + "'.");
            } else if (args[1].equalsIgnoreCase("go")) {
                String key = args[2];
                if (quickmaps.get(key) == null) {
                    chr.chatMessage(BlackOnWhite, "[SaveMap] There is no map saved as key '" + args[2] + "'.");
                    return;
                }
                Field toField = chr.getOrCreateFieldByCurrentInstanceType((quickmaps.get(key)));
                Portal portal = chr.getField().getDefaultPortal();
                chr.warp(toField, portal);
            } else if (args[1].equalsIgnoreCase("list")) {
                Set keys = quickmaps.keySet();
                chr.chatMessage(BlackOnWhite, "[SaveMap] " + quickmaps.size() + " saved maps.");
                for (Object maps : keys) {
                    chr.chatMessage(BlackOnWhite, "[SaveMap] Stored map: " + quickmaps.get(maps) + " as '" + maps + "'.");
                }
            } else {
                chr.chatMessage(BlackOnWhite, "Incorrect Syntax: !SaveMap <save/go> <key>");
                chr.chatMessage(BlackOnWhite, "To see the list of saved maps, use: !SaveMap list");
            }
        }
    }

    @Command(names = {"warriorequips"}, requiredType = Tester)
    public static class WarriorEquips extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] warEquips = new int[]{
                    1302000,
                    1312000,
                    1322000,
                    1402000,
                    1412000,
                    1422000,
                    1432000,
                    1442000,
                    1542000,
                    1232000,
                    1582000,
                    1353400,
                    1352500,
            };
            for (int warEquip : warEquips) {
                Item item = ItemData.getItemDeepCopy(warEquip);
                chr.addItemToInventory(item);
            }
        }
    }

    @Command(names = {"mageequips"}, requiredType = Tester)
    public static class MageEquips extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] mageEquips = new int[]{
                    1382000,
                    1372000,
                    1552000,
                    1252000,
                    1262000,
                    1353200,
            };
            for (int mageEquip : mageEquips) {
                Item item = ItemData.getItemDeepCopy(mageEquip);
                chr.addItemToInventory(item);
            }
        }
    }

    @Command(names = {"archerequips"}, requiredType = Tester)
    public static class ArcherEquips extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] archerEquips = new int[]{
                    1452000,
                    1462000,
                    1522000,
                    1352004,
                    1592000,
                    1353700,
            };
            for (int archerEquip : archerEquips) {
                Item item = ItemData.getItemDeepCopy(archerEquip);
                chr.addItemToInventory(item);
            }
        }
    }

    @Command(names = {"thiefequips"}, requiredType = Tester)
    public static class ThiefEquips extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] thiefEquips = new int[]{
                    1472000,
                    1332000,
                    1342000,
                    1242000,
                    1362000,
                    1352100
            };
            for (int thiefEquip : thiefEquips) {
                Item item = ItemData.getItemDeepCopy(thiefEquip);
                chr.addItemToInventory(item);
            }
        }
    }

    @Command(names = {"pirateequips"}, requiredType = Tester)
    public static class PirateEquips extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            int[] pirateEquips = new int[]{
                    1482000,
                    1353100,
                    1492000,
                    1222000,
                    1352600,
                    1532000,
                    1242000,
            };
            for (int pirateEquip : pirateEquips) {
                Item item = ItemData.getItemDeepCopy(pirateEquip);
                chr.addItemToInventory(item);
            }
        }
    }

    @Command(names = {"clearinv"}, requiredType = Tester)
    public static class ClearInv extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage(Notice2, "Syntax Error: !ClearInv <Inventory Type> <Start Index> <End Index>");
                return;
            }
            InvType invType = InvType.getInvTypeByString(args[1]);
            if (invType == null) {
                chr.chatMessage("Please fill in a correct inventory type:  equip / use / etc / setup / cash");
                return;
            }
            short startIndex = Short.parseShort(args[2]);
            short endIndex = Short.parseShort(args[3]);
            for (int i = startIndex; i < endIndex; i++) {
                Item removeItem = chr.getInventoryByType(invType).getItemBySlot(i);
                if (removeItem != null) {
                    chr.consumeItem(removeItem);
                }
            }
            chr.dispose();
        }
    }

    @Command(names = {"mobinfo"}, requiredType = Player)
    public static class MobInfo extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Rect rect = new Rect(
                    chr.getPosition().deepCopy().getX() - 200,
                    chr.getPosition().deepCopy().getY() - 200,
                    chr.getPosition().deepCopy().getX() + 200,
                    chr.getPosition().deepCopy().getY() + 200
            );
            Mob mob = chr.getField().getMobs().stream().filter(m -> rect.hasPositionInside(m.getPosition())).findFirst().orElse(null);
            Char controller = chr.getField().getLifeToControllers().getOrDefault(mob, null);
            if (mob != null) {
                chr.chatMessage(SpeakerChannel, String.format("Mob ID: %s | Template ID: %s | Level: %d | HP: %s/%s " +
                                        "| MP: %s/%s | Left: %s | PDR: %s | MDR: %s " +
                                        "| Controller: %s | Exp : %s | NX: %s",
                                NumberFormat.getNumberInstance(Locale.US).format(mob.getObjectId()),
                                NumberFormat.getNumberInstance(Locale.US).format(mob.getTemplateId()),
                                mob.getLevel(),
                                NumberFormat.getNumberInstance(Locale.US).format(mob.getHp()),
                                NumberFormat.getNumberInstance(Locale.US).format(mob.getMaxHp()),
                                NumberFormat.getNumberInstance(Locale.US).format(mob.getMp()),
                                NumberFormat.getNumberInstance(Locale.US).format(mob.getMaxMp()),
                                mob.isLeft(),
                                mob.getPdr(),
                                mob.getMdr(),
                                controller == null ? "null" : chr.getName(),
                                mob.getForcedMobStat().getExp(),
                                mob.getNxDropAmount()
                        )
                );
            } else {
                chr.chatMessage(SpeakerChannel, "Could not find mob.");
            }
        }
    }

    @Command(names = {"npcinfo"}, requiredType = Player)
    public static class NpcInfo extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Rect rect = new Rect(
                    chr.getPosition().deepCopy().getX() - 200,
                    chr.getPosition().deepCopy().getY() - 200,
                    chr.getPosition().deepCopy().getX() + 200,
                    chr.getPosition().deepCopy().getY() + 200
            );
            Npc npc = chr.getField().getNpcs().stream().filter(m -> rect.hasPositionInside(m.getPosition())).findFirst().orElse(null);
            Char controller = chr.getField().getLifeToControllers().getOrDefault(npc, null);
            if (npc != null) {
                chr.chatMessage(SpeakerChannel, String.format("Npc ID: %s | Template ID: %s | Left: %s | Pos: (%d, %d) " +
                                        "| Controller: %s",
                                NumberFormat.getNumberInstance(Locale.US).format(npc.getObjectId()),
                                NumberFormat.getNumberInstance(Locale.US).format(npc.getTemplateId()),
                                npc.isLeft(),
                                npc.getPosition().getX(),
                                npc.getPosition().getY(),
                                controller == null ? "null" : chr.getName()
                        )
                );
            } else {
                chr.chatMessage(SpeakerChannel, "Could not find mob.");
            }
        }
    }

    @Command(names = {"check", "dispose", "fix"}, requiredType = AccountType.Player)
    public static class Dispose extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.dispose();
            Map<BaseStat, Integer> basicStats = chr.getTotalBasicStats();
            StringBuilder sb = new StringBuilder();
            List<BaseStat> sortedList = Arrays.stream(BaseStat.values()).sorted(Comparator.comparing(Enum::toString)).collect(Collectors.toList());
            chr.chatMessage(Mob, String.format("X=%d, Y=%d, Stats: ", chr.getPosition().getX(), chr.getPosition().getY()));
            for (BaseStat bs : sortedList) {
                chr.chatMessage(Mob, (String.format("%s = %d, ", bs, basicStats.getOrDefault(bs, 0))));
            }
            ScriptManagerImpl smi = chr.getScriptManager();
            // all but field
            smi.stop(ScriptType.Portal);
            smi.stop(ScriptType.Npc);
            smi.stop(ScriptType.Reactor);
            smi.stop(ScriptType.Quest);
            smi.stop(ScriptType.Item);
        }
    }

    @Command(names = {"getnpcsinrect", "getnpcs"}, requiredType = Tester)
    public static class GetNPCs extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Rect rect = GameConstants.MOB_CHECK_RECT;

            List<Life> lifeList = chr.getField().getLifesInRect(chr.getRectAround(rect));
            chr.chatMessage("NPCs around you:");
            for (Life life : lifeList) {
                if (life instanceof Npc) {
                    chr.chatMessage(life.toString());
                }
            }
        }
    }

    @Command(names = {"completequest"}, requiredType = Tester)
    public static class CompleteQuest extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.getQuestManager().completeQuest(Integer.parseInt(args[1]));
        }
    }

    @Command(names = {"removequest"}, requiredType = Tester)
    public static class RemoveQuest extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.getQuestManager().removeQuest(Integer.parseInt(args[1]));
        }
    }

    @Command(names = {"sethonor", "honor"}, requiredType = Tester)
    public static class SetHonor extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage(SpeakerChannel, "Format: !sethonor <honor exp>");
                return;
            }
            int honor = Integer.parseInt(args[1]);
            chr.setHonorExp(honor);
            chr.write(WvsContext.characterHonorExp(honor));
        }
    }

    @Command(names = {"startquest"}, requiredType = Tester)
    public static class StartQuest extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage(SpeakerChannel, "Format: !startquest <quest id>");
                return;
            }
            Quest q = QuestData.createQuestFromId(Integer.parseInt(args[1]));
            if (q != null) {
                chr.getQuestManager().addQuest(q);
            } else {
                chr.chatMessage("Could not find quest with id " + args[1] + ", but still adding it.");
                chr.getScriptManager().startQuestNoCheck(Integer.parseInt(args[1]));
            }
        }
    }

    @Command(names = {"bypassskillcd", "ignoreskillcd", "bypasskillcd", "cd"}, requiredType = Tester)
    public static class BypassSkillCD extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.setSkillCDBypass(!chr.hasSkillCDBypass());
            if (chr.hasSkillCDBypass()) {
                chr.getSkillCoolTimes().keySet().forEach(chr::resetSkillCoolTime);
            }
            chr.chatMessage(Notice2, "Skill Cooldown bypass: " + chr.hasSkillCDBypass());
            chr.dispose();
        }
    }

    @Command(names = {"toggledamagecap"}, requiredType = Tester)
    public static class ToggleDamageCap extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.chatMessage("Damage cap can't be removed by CTS anymore :(");
        }
    }

    @Command(names = {"shop"}, requiredType = Tester)
    public static class Shop extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.getScriptManager().openShop(1011100);
        }
    }

    @Command(names = {"reloadcs"}, requiredType = Admin)
    public static class ReloadCS extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Server.getInstance().initCashShop();
        }
    }

    // lie detector
    @Command(names = {"ld", "liedetector"}, requiredType = GameMaster)
    public static class LD extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 1) {
                chr.chatMessage(SpeakerChannel, "Not enough args! Use !ld <name> or !ld @me to test.");
                return;
            }

            String name = args[1];
            Char chrToLD = chr;

            if (!name.equals("@me")) {
                chrToLD = Server.getInstance().getWorldById(chr.getClient().getWorldId()).getCharByName(name);

                if (chrToLD == null) {
                    chr.chatMessage(SpeakerChannel, String.format("Character '%s' is not online.", name));
                    return;
                }
            }

            if (chrToLD.sendLieDetector()) {
                chr.chatMessage(SpeakerChannel, String.format("Sent lie detector to '%s'.", chrToLD.getName()));
            } else {
                chr.chatMessage(SpeakerChannel, "Lie detector failed.");
            }
        }
    }

    @Command(names = {"ban"}, requiredType = GameMaster)
    public static class Ban extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 5) {
                chr.chatMessage(SpeakerChannel, "Not enough args! Use !ban <name> <amount> <min/hour/day/year> <reason>");
                return;
            }
            String name = args[1];
            int amount = Integer.parseInt(args[2]);
            String amountType = args[3].toLowerCase();
            StringBuilder builder = new StringBuilder();
            for (int i = 4; i < args.length; i++) {
                builder.append(args[i] + " ");
            }
            String reason = builder.toString();
            reason = reason.substring(0, reason.length() - 1); // gets rid of the last space
            if (reason.length() > 255) {
                chr.chatMessage(SpeakerChannel, "That ban reason is too long.");
                return;
            }
            Char banChr = Server.getInstance().getWorldById(chr.getClient().getWorldId()).getCharByName(name);
            boolean online = true;
            if (banChr == null) {
                online = false;
                banChr = Char.getFromDBByName(name);
                if (banChr == null) {
                    chr.chatMessage(SpeakerChannel, "Could not find that character.");
                    return;
                }
            }
            User banUser = banChr.getUser();
            LocalDateTime banDate = LocalDateTime.now();
            switch (amountType) {
                case "m":
                case "min":
                case "mins":
                    banDate = banDate.plusMinutes(amount);
                    break;
                case "h":
                case "hour":
                case "hours":
                    banDate = banDate.plusHours(amount);
                    break;
                case "d":
                case "day":
                case "days":
                    banDate = banDate.plusDays(amount);
                    break;
                case "y":
                case "year":
                case "years":
                    banDate = banDate.plusYears(amount);
                    break;
                default:
                    chr.chatMessage(SpeakerChannel, String.format("Unknown date type %s", amountType));
                    break;
            }
            banUser.setBanExpireDate(FileTime.fromDate(banDate));
            banUser.setBanReason(reason);
            banUser.getOffenseManager().addOffense(reason, chr.getId());
            chr.chatMessage(SpeakerChannel, String.format("Character %s has been banned. Expire date: %s", name, banDate));
            if (online) {
                banChr.write(WvsContext.returnToTitle());
            }
        }
    }

    @Command(names = {"killall"}, requiredType = Tester)
    public static class KillMobs extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            List<Mob> mobs = new ArrayList<>(chr.getField().getMobs());
            for (Mob mob : mobs) {
                mob.die(false);
            }
        }
    }

    @Command(names = {"findplayer"}, requiredType = Tester)
    public static class FindPlayer extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            Char victim = chr.getWorld().getCharByName((args[1]));
            if (victim != null) {
                chr.chatMessage(GameDesc, victim.getName() + " is in " + StringData.getMapStringById(victim.getFieldID()) + "   (Channel " + victim.getClient().getChannelInstance().getChannelId() + ")");
            } else {
                chr.chatMessage(WhiteOnGreen, "Player is offline");
            }
        }
    }

    @Command(names = {"bannernotice"}, requiredType = Tester)
    public static class BannerNotice extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.getWorld().broadcastPacket(WvsContext.broadcastMessage(4, chr.getClient().getChannel(), StringUtil.joinStringFrom(args, 1), false));
        }
    }

    @Command(names = {"mobstat"}, requiredType = Tester)
    public static class MobStatTest extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            List<Mob> mobs = new ArrayList<>(chr.getField().getMobs());
            if (mobs.size() > 0) {
                Mob mob = mobs.get(0);
                MobTemporaryStat mts = mob.getTemporaryStat();
                Option o = new Option();
                o.nOption = 1000;
                o.rOption = 145;
                o.slv = 1;
                o.tOption = 5;

                o.wOption = 1000;

                o.mOption = 1000;
                o.bOption = 1000;
                o.nReason = 1000;
                mts.addMobSkillOptionsAndBroadCast(MobStat.PCounter, o);
            } else {
                chr.chatMessage("Could not find a mob.");
            }
        }
    }

    @Command(names = {"fp", "findportal"}, requiredType = Tester)
    public static class FindPortal extends AdminCommand { // FindPortal
        public static void execute(Char chr, String[] args) {
            if (args.length < 1) {
                chr.chatMessage(SpeakerChannel, "Invalid args. Use !findportal <id/name>");
                return;
            }
            Field field = chr.getField();
            Portal portal;
            String query = args[1];
            if (Util.isNumber(query)) {
                portal = field.getPortalByID(Integer.parseInt(query));
            } else {
                portal = field.getPortalByName(query);
            }
            if (portal == null) {
                chr.chatMessage(SpeakerChannel, "Was not able to find portal " + query);
                return;
            }
            chr.chatMessage(SpeakerChannel, "Portal Name: " + portal.getName());
            chr.chatMessage(SpeakerChannel, "Portal ID: " + NumberFormat.getNumberInstance(Locale.US).format(portal.getId()));
            chr.chatMessage(SpeakerChannel, "Portal target map: " + NumberFormat.getNumberInstance(Locale.US).format(portal.getTargetMapId()));
            chr.chatMessage(SpeakerChannel, "Portal position: " + portal.getX() + ", " + portal.getY());
            chr.chatMessage(SpeakerChannel, "Portal script: " + portal.getScript());
            chr.chatMessage(SpeakerChannel, ".");
            log.info(portal.getScript());
        }
    }

    @Command(names = {"showbuffs"}, requiredType = Tester)
    public static class ShowBuffs extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Set<Integer> buffs = new HashSet<>();
            for (List<Option> options : tsm.getCurrentStats().values()) {
                for (Option o : options) {
                    if (o.rOption != 0) {
                        buffs.add(o.rOption);
                    } else {
                        buffs.add(o.nReason);
                    }
                }
            }
            StringBuilder sb = new StringBuilder("Current Buffs: ");
            for (int id : buffs) {
                String skillName = StringData.getSkillStringById(id) != null ? StringData.getSkillStringById(id).getName() : "Unknown Skill ID";
                sb.append(skillName).append(" (").append(id).append("), ");
            }
            chr.chatMessage(sb.substring(0, sb.toString().length() - 2));
            sb = new StringBuilder("CTS: ");
            for (CharacterTemporaryStat cts : tsm.getCurrentStats().keySet()) {
                sb.append(cts.toString()).append(", ");
            }
            chr.chatMessage(sb.substring(0, sb.toString().length() - 2));
        }
    }

    @Command(names = {"tohex"}, requiredType = Tester)
    public static class ToHex extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int arg = Integer.parseInt(args[1]);
            byte[] arr = new byte[4];
            arr[3] = (byte) ((arg >> 24) & 0xFF);
            arr[2] = (byte) ((arg >> 16) & 0xFF);
            arr[1] = (byte) ((arg >> 8) & 0xFF);
            arr[0] = (byte) (arg & 0xFF);
            chr.chatMessage(Util.readableByteArray(arr));
        }
    }

    @Command(names = {"fromhex"}, requiredType = Tester)
    public static class FromHex extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length == 1) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i].trim());
            }
            String s = sb.toString();
            s = s.replace("|", " ");
            s = s.replace(" ", "");
            int len = s.length();
            int[] arr = new int[len / 2];
            for (int i = 0; i < len; i += 2) {
                arr[i / 2] = ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            int num = 0;
            for (int i = 0; i < arr.length; i++) {
                num += arr[i] << (i * 8);
            }
            chr.chatMessage("" + num);
        }
    }

    @Command(names = {"fromhexbe", "hexbe"}, requiredType = Tester)
    public static class FromHexBE extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length == 1) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i].trim());
            }
            String s = sb.toString();
            s = s.replace("|", " ");
            s = s.replace(" ", "");
            long num = Long.parseLong(s, 16);
            chr.chatMessage("" + num);
        }
    }

    @Command(names = {"remote", "testremote"}, requiredType = Tester)
    public static class RemoteEnterFieldTest extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Char other = new Char();
            int id = 1337;
            other.setId(id);
            other.setPosition(chr.getPosition().deepCopy());
            other.setMoveAction(chr.getMoveAction());
            other.setFoothold(chr.getFoothold());
            other.setAvatarData(new AvatarData());
            other.getAvatarData().setAvatarLook(chr.getAvatarData().getAvatarLook());
            CharacterStat oldCs = chr.getAvatarData().getCharacterStat();
            CharacterStat cs = new CharacterStat();
            cs.setCharacterId(id);
            cs.setCharacterIdForLog(id);
            cs.setName("[Remote]" + chr.getName());
            cs.setJob(chr.getJob());
            cs.setLevel(chr.getLevel());
            cs.setGender(oldCs.getGender());
            cs.setFace(oldCs.getFace());
            cs.setHair(oldCs.getHair());
            cs.setSkin(oldCs.getSkin());
            other.setJobHandler(chr.getJobHandler());
            other.setTemporaryStatManager(chr.getTemporaryStatManager());
            other.getAvatarData().setCharacterStat(cs);
            other.setField(chr.getField());
            chr.setCopy(other);
            chr.write(UserPool.userLeaveField(other));
            chr.write(UserPool.userEnterField(other));
        }
    }


    @Command(names = {"showfh", "showfootholds"}, requiredType = Tester)
    public static class ShowFootholds extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Field field = chr.getField();
            for (Foothold fh : field.getFootholds()) {
                Drop drop1 = new Drop(0, 10);
                drop1.setPosition(new Position(fh.getX1(), fh.getY1()));
                drop1.setFh(fh.getId());
                Drop drop2 = new Drop(0, 100);
                drop2.setPosition(new Position(fh.getX2(), fh.getY2()));
                drop2.setFh(fh.getId());
                field.drop(drop1, drop1.getPosition());
                field.drop(drop2, drop2.getPosition());
            }
        }
    }


    @Command(names = {"removedrops", "drops"}, requiredType = Tester)
    public static class RemoveDrops extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Set<net.swordie.ms.life.drop.Drop> drops = chr.getField().getDrops();
            drops.forEach(drop -> chr.getField().removeLife(drop));
        }
    }

    @Command(names = {"lookupreactor", "reactors"}, requiredType = Tester)
    public static class LookUpReactor extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.getField().getReactors().forEach(reactor -> chr.chatMessage(reactor.toString()));
        }
    }

    @Command(names = {"chuc", "starforce", "sf"}, requiredType = Tester)
    public static class StarForceEquip extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 3) {
                chr.chatMessage("Not enough args! Usage: !sf <equip position in inventory> <star force>");
                return;
            }
            Item item = chr.getEquipInventory().getItemBySlot(Short.parseShort(args[1]));
            if (item == null) {
                chr.chatMessage("No item found in slot " + args[1]);
                return;
            }
            Equip equip = (Equip) item;
            equip.setChuc(Short.parseShort(args[2]));
            equip.setTuc((short) 0);
            equip.recalcEnchantmentStats();
            equip.updateToChar(chr);
        }
    }

    @Command(names = {"openui", "ui"}, requiredType = Tester)
    public static class OpenUI extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage("Needs a UI id.");
                return;
            }
            chr.write(FieldPacket.openUI(Integer.parseInt(args[1])));
        }
    }

    @Command(names = {"closeui"}, requiredType = Tester)
    public static class CloseUI extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage("UI id required.");
                return;
            }
            chr.write(FieldPacket.closeUI(Integer.parseInt(args[1])));
        }
    }

    @Command(names = {"af", "setaf"}, requiredType = Tester)
    public static class SetArcaneForce extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 3) {
                chr.chatMessage("!af <equip position> <amount>");
                return;
            }
            Equip equip = (Equip) chr.getEquipInventory().getItemBySlot(Short.parseShort(args[1]));
            equip.setArc(Short.parseShort(args[2]));
            equip.updateToChar(chr);
        }
    }

    @Command(names = {"bonusSkill"}, requiredType = Tester)
    public static class UseBonusSkill extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage("!bonusSkill <SkillId>");
                return;
            }
            chr.write(UserLocal.userBonusAttackRequest(Integer.parseInt(args[1])));
        }
    }

    @Command(names = {"randAreaSkill"}, requiredType = Tester)
    public static class UseRandAreaSkill extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage("!randAreaSkill <SkillId>");
                return;
            }
            chr.write(UserLocal.userRandAreaAttackRequest(Util.getRandomFromCollection(chr.getField().getMobs()), Integer.parseInt(args[1])));
        }
    }

    @Command(names = {"script"}, requiredType = Tester)
    public static class StartScriptTest extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 3) {
                chr.chatMessage("!script <type> <name>");
                return;
            }
            ScriptType st = null;
            for (ScriptType type : ScriptType.values()) {
                if (type.toString().equalsIgnoreCase(args[1])) {
                    st = type;
                    break;
                }
            }
            if (st == null) {
                StringBuilder str = new StringBuilder();
                for (ScriptType t : ScriptType.values()) {
                    str.append(t.toString()).append(", ");
                }
                String res = str.substring(0, str.length() - 2);
                chr.chatMessage(String.format("Unknown script type %s, known types: %s", args[1], res));
                return;
            }
            chr.getScriptManager().startScript(0, args[2], st);
        }
    }

    @Command(names = {"broadcast", "say", "announce"}, requiredType = GameMaster)
    public static class BroadcastMessage extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage("Usage: !broadcast <msg>");
                return;
            }
            StringBuilder sb = new StringBuilder(String.format("[GM %s]:", chr.getName()));
            for (int i = 1; i < args.length; i++) {
                sb.append(" ").append(args[i]);
            }
            chr.getWorld().broadcastPacket(UserLocal.chatMsg(BlackOnWhite, sb.toString()));
        }
    }

    @Command(names = {"skillinfo", "skillinfomode", "si"}, requiredType = Tester)
    public static class EnterSkillInfoMode extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.setSkillInfoMode(!chr.isSkillInfoMode());
            chr.chatMessage(AdminChat, "skill info: " + chr.isSkillInfoMode());
        }
    }

    @Command(names = {"debug", "debugmode"}, requiredType = Tester)
    public static class DebugMode extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            chr.setDebugMode(!chr.isDebugMode());
            chr.chatMessage(AdminChat, "skill info: " + chr.isDebugMode());
        }
    }

    @Command(names = {"tp", "teleport"}, requiredType = Tester)
    public static class Teleport extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 3) {
                chr.chatMessage("Usage: !tp <x> <y>");
                return;
            }
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            chr.write(FieldPacket.teleport(new Position(x, y), chr));
        }
    }

    @Command(names = {"cleardrop", "cleardrops"}, requiredType = Tester)
    public static class ClearDrops extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Field field = chr.getField();
            Set<Drop> removeDrops = new HashSet<>(field.getDrops());
            for (Drop drop : removeDrops) {
                field.removeLife(drop);
            }
        }
    }

    @Command(names = {"gift"}, requiredType = Tester)
    public static class GiftBox extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            String targetName = args[1];
            int worldID = chr.getClient().getChannelInstance().getWorldId().getVal();
            World world = Server.getInstance().getWorldById(worldID);
            Char targetChr = world.getCharByName(targetName);

            // Target doesn't exist
            if (targetChr == null) {
                chr.chatMessage(String.format("%s is not online.", targetName));
                chr.dispose();
                return;
            }

            int targetId = targetChr.getId();

            for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                HotTimeReward reward = new HotTimeReward();
                reward.setCharId(targetId);
                reward.setStartTime(reward.getStartTime());
                reward.setEndTime(FileTime.fromDate(reward.getStartTime().toLocalDateTime().plusDays(30)));
                reward.setRewardType(HotTimeRewardType.GAME_ITEM);
                reward.setItemId(2000000);
                reward.setQuantity(69);
                reward.setMaplePointAmount(0);
                reward.setMesoAmount(0);
                reward.setExpAmount(0);
                reward.setDescription("This is a new reward description.");

                DatabaseManager.saveToDB(reward);
                targetChr.checkHotTimeRewards();
            }
        }
    }

    @Command(names = {"checkgift"}, requiredType = Player)
    public static class CheckGift extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            // check if the character has gifts
            chr.checkHotTimeRewards();

            List<HotTimeReward> rewards = chr.getHotTimeRewards();
            if (rewards.size() > 0) {
                chr.chatMessage("You have %d hot time rewards available!", rewards.size());
            } else {
                chr.chatMessage("No hot time rewards available!");
            }
        }
    }

    @Command(names = {"removegift"}, requiredType = Player)
    public static class RemoveGift extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            // check if the character has gifts
            List<HotTimeReward> rewards = chr.getHotTimeRewards();
            if (rewards.size() > 0) {
                HotTimeReward reward = chr.getHotTimeRewards().get(0);
                chr.chatMessage("Removed the first reward in hot time rewards!");
                chr.chatMessage("id: %d", reward.getId());
                chr.getHotTimeRewards().remove(0);
            } else {
                chr.chatMessage("No hot time rewards available!");
            }
        }
    }

    @Command(names = {"gifttest"}, requiredType = Tester)
    public static class TestGift extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            HotTimeReward reward = new HotTimeReward();
            reward.setId(1);
            reward.setStartTime(reward.getStartTime());
            reward.setEndTime(FileTime.fromDate(reward.getStartTime().toLocalDateTime().plusDays(30)));
            reward.setRewardType(HotTimeRewardType.GAME_ITEM);
            reward.setItemId(2000000);
            reward.setQuantity(69);
            reward.setMaplePointAmount(Integer.parseInt(args[2]));
            reward.setMesoAmount(0);
            reward.setExpAmount(0);
            reward.setDescription("This is a new reward description.");

            chr.write(WvsContext.sendHotTimeRewardResult(Integer.parseInt(args[1]), reward));
        }
    }


    @Command(names = {"nxtest", "testnx"}, requiredType = Tester)
    public static class CheckNXValue extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            // just copy pasted from Mob::getNxDropAmount
            if (args.length < 3) {
                chr.chatMessage("Usage: !testnx <level> <hp>");
                return;
            }
            int level = Integer.parseInt(args[1]);
            long hp = Long.parseLong(args[2]);
            int base = (int) (((level / 2D) * (Math.pow(hp, (1 / 7D))))) * GameConstants.NX_DROP_MULTIPLIER;
            int max = base + (base / 10);
            chr.chatMessage("min = %d, avg = %d, max = %d", base, (base + max) / 10, max);
        }
    }

    /*
    if (getExp() == 0) {
    return 0;
    }
    int base = (int) ((Math.sqrt(getMaxHp() / 100D)) * ((double) getMaxHp() / (getExp() * getLevel())));
    return Util.getRandom(base, (base + base / 10));

        long hp = getMaxHp();
        ForcedMobStat fms = getForcedMobStat();
        int base = (int) (((fms.getLevel() / 2D) * (Math.pow(hp, (1 / 7D))))) * GameConstants.NX_DROP_MULTIPLIER;
        return Util.getRandom(base, (base + base / 10)); // base + 10% random
     */

    @Command(names = {"setdamageskin", "damageskin", "setds", "ds"}, requiredType = Tester)
    public static class SetDamageSkin extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 1) {
                chr.chatMessage("Usage: <damage skin id>");
                chr.chatMessage("[INFO] damage skin ids range from 1 to 219");
                return;
            }
            String dmgSkinIdxStr = args[1];
            if (!Util.isNumber(dmgSkinIdxStr)) {
                chr.chatMessage("Usage: <damage skin id>");
                chr.chatMessage("[INFO] damage skin ids range from 1 to 219");
                return;
            }
            int dmgSkinIdx = Integer.parseInt(dmgSkinIdxStr);
            QuestManager qm = chr.getQuestManager();
            Quest q = qm.getQuests().getOrDefault(QuestConstants.DAMAGE_SKIN, null);
            if (q == null) {
                q = new Quest(QuestConstants.DAMAGE_SKIN, QuestStatus.Started);
                qm.addQuest(q);
            }
            q.setQrValue(dmgSkinIdxStr);
            chr.setDamageSkin(dmgSkinIdx);
            chr.write(UserPacket.setDamageSkin(chr));
            chr.write(WvsContext.questRecordMessage(q));
        }
    }

    @Command(names = {"testparty", "party"}, requiredType = Tester)
    public static class TestParty extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            int id = chr.getId() == 1 ? 2 : 1;
            Char other = Char.getFromDBById(id);
            Party party = chr.getParty();
            if (other == null) {
                chr.chatMessage("Make a second character.");
                return;
            }
            if (party == null) {
                chr.chatMessage("Be in a party.");
                return;
            }
//            party.addPartyMember(other);
            chr.write(WvsContext.partyResult(PartyResult.userMigration(party)));
        }
    }

    @Command(names = {"sendQRvalue", "qr"}, requiredType = Admin)
    public static class SendQRValue extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 3 || !Util.isNumber(args[1])) {
                chr.chatMessage("Usage: !qr <questid> <qrValue>");
                return;
            }
            int questId = Integer.parseInt(args[1]);


            QuestManager qm = chr.getQuestManager();
            Quest q = qm.getQuests().getOrDefault(questId, null);
            if (q == null) {
                q = new Quest(questId, QuestStatus.Started);
                qm.addQuest(q);
            }
            q.setQrValue(args[2]);
            chr.write(WvsContext.questRecordMessage(q));
            chr.write(WvsContext.message(MessageType.QUEST_RECORD_EX_MESSAGE,
                    q.getQRKey(), q.getQRValue(), (byte) 0));
            chr.chatMessage(String.format("Sent QRValue with  QuestId %d, QrValue %s", questId, q.getQRValue()));
        }
    }

    @Command(names = {"amountonline", "playercount", "count", "online", "onlineamount"}, requiredType = Tester)
    public static class AmountOnline extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            StringBuilder sb = new StringBuilder();
            for (World w : Server.getInstance().getWorlds()) {
                chr.chatMessage(Notice, "----------------------");
                for (Channel c : w.getChannels()) {
                    chr.chatMessage(Notice, "Channel %s: %d players.", c.getName(), c.getChars().size());
                }
            }
        }
    }

    @Command(names = {"players", "onlineplayers"}, requiredType = Tester)
    public static class Players extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            StringBuilder sb = new StringBuilder();
            for (World w : Server.getInstance().getWorlds()) {
                chr.chatMessage(Notice, "----------------------");
                for (Channel c : w.getChannels()) {
                    StringBuilder chars = new StringBuilder();
                    for (Char aChar : c.getChars().values()) {
                        chars.append(aChar.getName()).append(", ");
                    }
                    chr.chatMessage(Notice, "Channel %s: %s.", c.getName(),
                            chars.substring(0, chars.length() != 0 ? chars.length() - 2 : 0));
                }
            }
        }
    }

    @Command(names = {"warptoplayer", "warpto"}, requiredType = Tester)
    public static class WarpToPlayer extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage("Usage: !warpto <playername>");
                return;
            }
            String targetName = args[1];
            int worldID = chr.getClient().getChannelInstance().getWorldId().getVal();
            World world = Server.getInstance().getWorldById(worldID);
            Char targetChr = world.getCharByName(targetName);

            // Target doesn't exist
            if (targetChr == null) {
                chr.chatMessage(String.format("%s is not online.", targetName));
                chr.dispose();
                return;
            }

            Position targetPosition = targetChr.getPosition();

            Field targetField = targetChr.getField();
            if (targetChr.getClient().getChannel() != chr.getClient().getChannel()) {
                // change channel & warp
                int fieldId = targetChr.getFieldID();
                chr.setInstance(null);
                chr.changeChannelAndWarp(targetChr.getClient().getChannel(), fieldId);

            } else if (targetChr.getFieldID() != chr.getFieldID()) {
                // warp & teleport
                chr.setInstance(null);
                chr.warp(targetField);
                chr.write(FieldPacket.teleport(targetPosition, chr));
            } else {
                // teleport
                chr.write(FieldPacket.teleport(targetPosition, chr));
            }
        }
    }

    @Command(names = {"guildstat", "setguildstat"}, requiredType = Tester)
    public static class SetGuildStat extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            Guild guild = chr.getGuild();
            if (guild == null) {
                chr.chatMessage("Be in a guild first.");
                return;
            }
            GuildMember gm = guild.getMemberByChar(chr);
            if (gm == null) {
                chr.chatMessage("You are not a member of your guild.");
                return;
            }
            if (args.length < 4) {
                chr.chatMessage("Usage: !guildstat <level> <ggp> <igp>");
                return;
            }
            guild.setLevel(Integer.parseInt(args[1]));
            guild.setGgp(Integer.parseInt(args[2]));
            gm.setIgp(Integer.parseInt(args[3]));
            guild.updateToMembers();
        }

    }


    @Command(names = {"givering", "ring"}, requiredType = Tester)
    public static class GiveRing extends AdminCommand {
        public static void execute(Char chr, String[] args) throws IOException, SQLException {
            if (args[1].contains("help")) {
                chr.chatMessage("Example @givering Shiina 1112002 \r\n This command uses 20,000nx\r\n Make sure the person you want to send this to has inventory space or you will waste 20,000nx\r\n You must equip the ring in the SAME SLOT as your partner then you must both re-login or CC");
                return;
            } else if (args.length < 3) {
                chr.chatMessage("Type @givering ringid to get the list of Ring ID's or Type @givering help for information on how to perform this command");
                return;
            }
            if (chr.getUser().getNxPrepaid() < 20000) {
                chr.chatMessage("You don't have enough NX!");
                return;
            }
            int itemId = Integer.parseInt(args[2]);
            if (!ItemConstants.isEffectRing(itemId)) {
                chr.chatMessage("Invalid itemID.");
                return;
            } else {
                Char fff = chr.getWorld().getCharByName(args[1]);
                if (fff == null) {
                    chr.chatMessage("Player must be online");
                    return;
                } else {
                    long[] ringID = new long[2];
                    try {
                        Char[] chrz = {fff, chr};
                        for (int i = 0; i < chrz.length; i++) {
                            Item item = ItemData.getEquipDeepCopyFromID(itemId, false);
                            Equip eq = (Equip) item;
                            DatabaseManager.saveToDB(eq);
                            if (ItemData.getEquipById(itemId) == null) {
                                chr.chatMessage("Invalid itemID.");
                                return;
                            }
                            chrz[i].chatMessage("id " + eq.getId());
                            ringID[i] = eq.getId();
                            if (eq == null || ringID == null) {
                                chr.chatMessage("Invalid ringID.");
                                return;
                            }
                            chrz[i].addItemToInventory(InvType.EQUIP, eq, false);
                            chrz[i].chatMessage("Successfully made a ring with " + chrz[i == 0 ? 1 : 0].getName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FriendshipRingRecord.addToDB(itemId, chr, fff.getName(), fff.getId(), ringID);
                }
            }
        }
    }


    @Command(names = {"calcdamage", "calc"}, requiredType = Tester)
    public static class ShowDamageCalc extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.setShowDamageCalc(!chr.isShowDamageCalc());
            chr.chatMessage(Notice2, "Showing damage calculation is now %s.", chr.isShowDamageCalc() ? "on" : "off");
        }
    }

    @Command(names = {"pnpc"}, requiredType = GameMaster)
    public static class PNPC extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            if (args.length < 2) {
                chr.chatMessage("Usage: !pnpc <id>");
                return;
            }
            int id = Integer.parseInt(args[1]);
            Npc npc = NpcData.getNpcDeepCopyById(id);
            if (npc == null) {
                chr.chatMessage("Could not find an npc with that ID.");
                return;
            }
            Field field = chr.getField();
            Position pos = chr.getPosition();
            npc.setPosition(pos.deepCopy());
            npc.setCy(chr.getPosition().getY());
            npc.setRx0(chr.getPosition().getX() + 50);
            npc.setRx1(chr.getPosition().getX() - 50);
            npc.setFh(chr.getFoothold());
            npc.setNotRespawnable(true);
            if (npc.getField() == null) {
                npc.setField(field);
            }
            field.spawnLife(npc, null);
            log.debug("npc has id " + npc.getObjectId());

            Session session = DatabaseManager.getSession();
            Transaction transaction = session.beginTransaction();

            Query npcQuery = session.createNativeQuery("INSERT INTO npc (npcid,mapid,x,y,cy,rx0,rx1,fh) VALUES (:npcid,:mapid,:x,:y,:cy,:rx0,:rx1,:fh)");
            npcQuery.setParameter("npcid", id);
            npcQuery.setParameter("mapid", field.getId());
            npcQuery.setParameter("x", pos.getX());
            npcQuery.setParameter("y", pos.getY());
            npcQuery.setParameter("cy", npc.getCy());
            npcQuery.setParameter("rx0", npc.getRx0());
            npcQuery.setParameter("rx1", npc.getRx1());
            npcQuery.setParameter("fh", npc.getFh());


            npcQuery.executeUpdate();

            transaction.commit();
            session.close();

        }
    }

    @Command(names = {"forcechase"}, requiredType = GameMaster)
    public static class ForceChase extends AdminCommand {
        public static void execute(Char chr, String[] args) {

            for (Mob m : chr.getField().getMobs()) {
                log.debug(m.isUserControll());
            }

            for (Mob m : chr.getField().getMobs()) {
                chr.getField().broadcastPacket(MobPool.forceChase(m.getObjectId(), false));
            }

        }
    }

    @Command(names = {"setcontroller"}, requiredType = GameMaster)
    public static class SetController extends AdminCommand {
        public static void execute(Char chr, String[] args) {

            String chrName = args[1];

            Char newController = chr.getField().getCharByName(chrName);
            if (newController == null) {
                chr.chatMessage("Character not found");
                return;
            }

            for (Mob m : chr.getField().getMobs()) {
                m.notifyControllerChange(newController);
                chr.getField().putLifeController(m, newController);
            }

        }
    }

    @Command(names = {"mobcontroller"}, requiredType = GameMaster)
    public static class MobController extends AdminCommand {
        public static void execute(Char chr, String[] args) {

            String chrName = args[1];

            for (Mob m : chr.getField().getMobs()) {
                Char controller = m.getField().getLifeToControllers().get(m);
                chr.chatMessage(m.getObjectId() + " : " + controller.getName());
            }

        }
    }

    @Command(names = {"bosscd"}, requiredType = GameMaster)
    public static class BossCd extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            chr.getAccount().clearCoolDowns();
        }
    }

    @Command(names = {"givenx"}, requiredType = Tester)
    public static class giveNx extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            String name = args[1];
            int amount = Integer.parseInt(args[2]);
            Char other = chr.getWorld().getCharByName(name);
            other.addNx(amount);
        }
    }

    @Command(names = {"givedp"}, requiredType = Tester)
    public static class givedp extends AdminCommand {
        public static void execute(Char chr, String[] args) {
            String name = args[1];
            int amount = Integer.parseInt(args[2]);
            Char other = chr.getWorld().getCharByName(name);
            other.addDP(amount);
        }
    }

    @Command(names = {"setpierce"}, requiredType = Admin)
    public static class setPierce extends AdminCommand {

        public static void execute(Char chr, String[] args) {
            String name = args[1];
            int amount = Integer.parseInt(args[2]);
            Char other = chr.getWorld().getCharByName(name);
            other.getAvatarData().getCharacterStat().setPierce(amount);
            chr.chatMessage("You have set " + amount + "% pierce to " + other.getName());
        }
    }
}
