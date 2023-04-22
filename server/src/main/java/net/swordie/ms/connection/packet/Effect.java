package net.swordie.ms.connection.packet;


import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.archer.Pathfinder;
import net.swordie.ms.client.jobs.adventurer.thief.BladeMaster;
import net.swordie.ms.client.jobs.adventurer.thief.Shadower;
import net.swordie.ms.client.jobs.adventurer.warrior.DarkKnight;
import net.swordie.ms.client.jobs.anima.HoYoung;
import net.swordie.ms.client.jobs.cygnus.NightWalker;
import net.swordie.ms.client.jobs.flora.Illium;
import net.swordie.ms.client.jobs.legend.Evan;
import net.swordie.ms.client.jobs.legend.Luminous;
import net.swordie.ms.client.jobs.legend.Phantom;
import net.swordie.ms.client.jobs.legend.Shade;
import net.swordie.ms.client.jobs.nova.AngelicBuster;
import net.swordie.ms.client.jobs.nova.Cadena;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.BattleMage;
import net.swordie.ms.client.jobs.resistance.Blaster;
import net.swordie.ms.client.jobs.resistance.WildHunter;
import net.swordie.ms.client.jobs.resistance.demon.DemonSlayer;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.TextEffectType;
import net.swordie.ms.enums.UserEffectType;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.swordie.ms.enums.UserEffectType.*;

public class Effect {

    private UserEffectType userEffectType;
    private String string;
    private List<Tuple<Integer, Integer>> list = new ArrayList<>();

    private int arg1;
    private int arg2;
    private int arg3;
    private int arg4;
    private int arg5;
    private int arg6;
    private int arg7;
    private int arg8;
    private int arg9;
    private int arg10;
    private int arg11;
    private int arg12;

    public void encode(OutPacket outPacket) { // v204.1
        outPacket.encodeByte(getUserEffectType().getVal());

        int skillID = getArg1();
        switch (getUserEffectType()) {
            case LevelUp:
                // Empty
                break;
            case SkillUse:
            case SkillUseBySummoned:
                encodeSkillUse(outPacket); // too long to put here
                break;
            case SkillAffected:
                outPacket.encodeInt(getArg1()); // skill id
                outPacket.encodeByte(getArg2()); // slv
                if (skillID == DemonSlayer.RAVEN_STORM || skillID == Shade.DEATH_MARK) {
                    outPacket.encodeInt(getArg3()); // nDelta
                }
                break;
            case SkillAffected_Ex:
                outPacket.encodeInt(getArg1()); // skill id
                outPacket.encodeByte(getArg2()); // slv
                outPacket.encodeInt(getArg3()); // positionX ?
                outPacket.encodeInt(getArg4()); // positionY ?
                break;
            case SkillAffected_Select:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                outPacket.encodeInt(getArg3());
                outPacket.encodeByte(getArg4());
                outPacket.encodeByte(getArg5());
                break;
            case SkillSpecialAffected:
                outPacket.encodeInt(getArg1()); // skill id
                outPacket.encodeByte(getArg2());// slv
                break;
            case Quest:
                outPacket.encodeByte(getList().size());
                for (Tuple<Integer, Integer> item : getList()) {
                    outPacket.encodeInt(item.getLeft()); // Item ID
                    outPacket.encodeInt(item.getRight()); // Quantity
                }
                break;
            case Pet:
                outPacket.encodeByte(getArg1());
                outPacket.encodeInt(getArg2());
                break;
            case SkillSpecial:
                outPacket.encodeInt(skillID); // skill id
                if (SkillConstants.isExplosionSkill(skillID)) {
                    outPacket.encodeInt(getArg2());
                    outPacket.encodeInt(getArg3());
                    outPacket.encodeInt(getArg4());
                } else if (skillID == BattleMage.DARK_SHOCK) {
                    outPacket.encodeInt(getArg2()); // slv
                    outPacket.encodeInt(getArg3()); // show effect
                    outPacket.encodePositionInt(new Position(getArg4(), getArg5())); // Origin Position -  arg4 = x, arg5 = y
                    outPacket.encodePositionInt(new Position(getArg6(), getArg7())); // Destination Position -  arg6 = x, arg7 = y
                } else if (skillID == 80002206 || skillID == 80000257 || skillID == 80000260 || skillID == 80002599) {
                    // M-Force Go!, Starlight Explosion, Petite Avatar, Remembering the Journey
                    outPacket.encodeInt(getArg2());
                    outPacket.encodeInt(getArg3());
                    outPacket.encodeInt(getArg4());

                }
                break;
            case Resist:
                // Empty
                break;
            case ProtectOnDieItemUse:
                outPacket.encodeByte(getArg1());
                outPacket.encodeByte(getArg2());
                outPacket.encodeByte(getArg3());
                outPacket.encodeInt(getArg4());     // ItemID
                break;
            case PlayPortalSE:
            case JobChanged:
            case QuestComplete:
                // Empty
                break;
            case IncDecHPEffect:
                outPacket.encodeByte(getArg1());    // amount being healed     0 = Miss
                break;
            case BuffItemEffect:
                outPacket.encodeInt(getArg1());     // ItemID
                break;
            case SquibEffect:
                outPacket.encodeString(getString());
                break;
            case MonsterBookCardGet:
                // Empty
                break;
            case LotteryUse:
                outPacket.encodeInt(getArg1());
                outPacket.encodeByte(getArg2());    // success
                if (getArg2() > 0) {
                    outPacket.encodeString(getString());
                }
                break;
            case ItemLevelUp:
                // Empty
                break;
            case ItemMaker:
                outPacket.encodeInt(getArg1());     // success or failure      0 = Success,  1 = Failure
                break;
            case FieldMesoItemConsumed:
                outPacket.encodeInt(getArg1());     // Meso Gained
                break;
            case ExpItemConsumed:
                // Empty
                break;
            case FieldExpItemConsumed:
                outPacket.encodeInt(getArg1());     // Exp Gained
                break;
            case ReservedEffect:
                outPacket.encodeByte(getArg1());    // bScreenCoord
                outPacket.encodeInt(getArg2());     // nRX
                outPacket.encodeInt(getArg3());     // nRY
                outPacket.encodeString(getString());// sEffect
                break;

            // 27

            case UpgradeTombItemUse:
                outPacket.encodeByte(getArg1());
                break;
            case BattlefieldItemUse:
                // Empty
                break;
            case unk30:
                outPacket.encodeString(getString());
                break;
            case AvatarOriented:
                outPacket.encodeString(getString());
                break;
            case AvatarOrientedRepeat:
                outPacket.encodeByte(getArg1());
                if (getArg1() > 0) {
                    outPacket.encodeString(getString());
                    outPacket.encodeInt(getArg2());
                    outPacket.encodeInt(getArg3());
                }
                break;
            case AvatarOrientedMultipleRepeat:
                outPacket.encodeString(getString());
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                break;
            case IncubatorUse:
                outPacket.encodeInt(getArg1());
                outPacket.encodeString(getString());
                break;
            case PlaySoundWithMuteBGM:
                outPacket.encodeString(getString());
                break;
            case PlayExclSoundWithDownBGM:
                outPacket.encodeString(getString());
                outPacket.encodeInt(getArg1());
                break;
            case SoulStoneUse:
                // Empty
                break;
            case IncDecHPEffect_EX:
                outPacket.encodeInt(getArg1()); // hp amount
                outPacket.encodeByte(getArg2()); // unk
                outPacket.encodeByte(getArg3()); // unk
                break;
            case IncDecHPRegenEffect:
                outPacket.encodeInt(getArg1()); // hp amount
                break;
            case EffectUOL:
                outPacket.encodeString(getString());
                outPacket.encodeByte(getArg1());
                outPacket.encodeInt(getArg2());
                outPacket.encodeInt(getArg3());
                if (getArg3() == 2) { // item sound
                    outPacket.encodeInt(getArg4()); // nItemID
                }
                break;
            case PvPRage:
                outPacket.encodeInt(getArg1());
                break;
            case PvPChampion:
                outPacket.encodeInt(getArg1());
                break;
            case PvPGradeUp:
            case PvPRevive:
            case PvPJobEffect:
                // Empty
                break;
            case FadeInOut:
                outPacket.encodeInt(getArg1());// tFadeIn
                outPacket.encodeInt(getArg2());// tDelay
                outPacket.encodeInt(getArg3());// tFadeOut
                outPacket.encodeByte(getArg4()); // nAlpha
                break;
            case MobSkillHit:
            case unk48:
                outPacket.encodeInt(getArg1()); // skill id  or  mob id?
                outPacket.encodeInt(getArg1()); // skill id  or  mob id?
                break;
            case BlindEffect:
                outPacket.encodeByte(getArg1()); //  set / remove
                break;
            case BossShieldCount:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg1());
                break;
            case ResetOnStateForOnOffSkill:
                // Empty
                break;
            case JewelCraft:
                outPacket.encodeByte(getArg1()); // Result  0,2 = Success,     5 = Unk error,      Other = Fail
                outPacket.encodeInt(getArg2()); // Item ID
                break;
            case ConsumeEffect:
                outPacket.encodeInt(getArg1()); // Item ID
                break;
            case PetBuff:
                // Empty
                break;
            case LotteryUIResult:
                outPacket.encodeByte(getArg1());
                outPacket.encodeByte(getArg2());
                outPacket.encodeInt(getArg3()); // Item ID ?
                outPacket.encodeInt(getArg4()); // Result Type
                break;
            case LeftMonsterNumber:
                outPacket.encodeInt(getArg1()); // Number on Arrow
                break;
            case ReservedEffectRepeat:
                outPacket.encodeString(getString());    // effect
                outPacket.encodeByte(getArg2() != 0);        // bDuration
                if (getArg2() != 0) {
                    outPacket.encodeByte(getArg1());        // bSomething2
                    if (getArg1() != 0) {
                        outPacket.encodeByte(getArg3());    // bShow?
                        outPacket.encodeInt(getArg4());     // nX
                        outPacket.encodeInt(getArg5());     // nY
                    }
                } else {
                    outPacket.encodeInt(getArg2());    // nDuration (seconds)
                    outPacket.encodeInt(getArg4());     // nX
                    outPacket.encodeInt(getArg5());     // nY
                }
                break;
            case RobbinsBomb:
                outPacket.encodeByte(getArg1()); // Reset/Delete
                outPacket.encodeInt(getArg2()); // BombCount
                outPacket.encodeByte(getArg3()); // number (unknown)
                break;
            case SkillMode:
                outPacket.encodeInt(getArg1()); // Skill ID
                outPacket.encodeInt(getArg2()); // Rotate (?)
                outPacket.encodeInt(getArg3()); // Skip Frame (?)
                break;
            case ActQuestComplete:
                outPacket.encodeInt(getArg1());
                break;
            case Point:
                outPacket.encodeInt(getArg1()); // PosX ?
                outPacket.encodeInt(getArg2()); // PosY ?
                break;
            case SpeechBalloon:
                outPacket.encodeByte(arg1);  // normal
                outPacket.encodeInt(arg2); // idx
                outPacket.encodeInt(arg3); // linkType
                outPacket.encodeString(string); // speech
                outPacket.encodeInt(arg4); // time
                outPacket.encodeInt(arg5); // align
                outPacket.encodeInt(arg6); // x
                outPacket.encodeInt(arg7); // y
                outPacket.encodeInt(arg8); // z
                outPacket.encodeInt(arg9); // lineSpace
                outPacket.encodeInt(arg10); // npcTemplateId
                outPacket.encodeInt(arg11); // ?
                outPacket.encodeInt(arg12);
                break;
            case TextEffect:
                outPacket.encodeString(getString());
                outPacket.encodeInt(getArg1()); // letter delay
                outPacket.encodeInt(getArg2()); // box duration
                outPacket.encodeInt(getArg3()); // Positioning on Client  ( 4 = middle )

                outPacket.encodeInt(getArg4()); // xPos
                outPacket.encodeInt(getArg5()); // yPos

                outPacket.encodeInt(getArg6()); // Align
                outPacket.encodeInt(getArg7()); // Line space
                outPacket.encodeInt(getArg8()); // Enter type (0 = fade in)
                outPacket.encodeInt(getArg9()); // Leave type?
                outPacket.encodeInt(getArg10()); // Type
                outPacket.encodeString("");
                outPacket.encodeInt(0); // v212+
                outPacket.encodeByte(false); // v212+
                break;
            case SkillPreLoopEnd:
                outPacket.encodeInt(getArg1()); // skill id
                outPacket.encodeInt(getArg2()); // time in ms
                break;
            case Aiming:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                outPacket.encodeInt(getArg3());
                outPacket.encodeInt(getArg4());
                outPacket.encodeInt(getArg5());
                outPacket.encodeInt(getArg6());
                break;
            case Unk66:
                outPacket.encodeInt(getArg1());
                outPacket.encodeShort(getArg2()); // position ?
                outPacket.encodeShort(getArg3());
                outPacket.encodeShort(getArg4()); // position2 ?
                outPacket.encodeShort(getArg5());
                break;
            case PickUpItem:
                // Empty
                break;
            case BattlePvP_IncDecHp:
                outPacket.encodeInt(getArg1());
                outPacket.encodeString(getString());
                break;
            case CatchEffect:
                // Empty
                break;
            case FailCatchEffect:
                // Empty
                break;
            case BiteAttack_ReceiveSuccess:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                break;
            case BiteAttack_ReceiveFail:
                outPacket.encodeByte(getArg1()); // success/fail
                if (getArg1() > 0) {
                    outPacket.encodeInt(getArg2());
                }
                break;
            case IncDecHPEffect_Delayed:
                outPacket.encodeShort(getArg1());
                outPacket.encodeInt(getArg2()); // HP healed ?
                outPacket.encodeByte(getArg3());
                outPacket.encodeByte(getArg4());
                outPacket.encodeByte(getArg5());
                break;
            case Unk74:
                outPacket.encodeInt(getArg1()); // type (0, 1, 2, 3,  default)
                outPacket.encodeByte(getArg2()); // item decode
                break;
            case MobSkillSpecial: // for 241/4, 242/4,5,15, 201/256 ('special')
                outPacket.encodeByte(getArg1()); // success/fail?
                outPacket.encodeInt(getArg2()); // MobSkillID
                outPacket.encodeInt(getArg3()); // MobSLV
                break;
            case BlackMageEffect:
                outPacket.encodeInt(getArg1()); // 100017 for Black Mage lasers effect
                outPacket.encodeInt(getArg2()); // 1  unk
                outPacket.encodeByte(getArg3()); // 1  unk
                outPacket.encodeByte(getArg4()); // 1  unk
                break;
            case ResistAbnormalStatus:
                outPacket.encodeInt(getArg1()); // SkillID?
                break;
            case Unk78:
                outPacket.encodeString(getString());
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                outPacket.encodeInt(getArg3());
                outPacket.encodeByte(getArg4());
                outPacket.encodeInt(getArg5());
                outPacket.encodeInt(getArg6());
                break;
            case RedChat:
                outPacket.encodeString(getString()); // msg
                break;
            case Unk81:
                outPacket.encodeString(getString());
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                break;
            case GoblinBatHit:
                outPacket.encodeInt(getArg1()); // SkillID 80002852
                outPacket.encodeInt(getArg2());
                outPacket.encodeInt(getArg3());
                break;
            case Unk82:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                outPacket.encodeInt(getArg3());
                break;
            case SkillMoveEffect:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                break;
            case Unk80:
            case UpgradePotionMsg:
            case MonsterBookSetComplete:
            case FamiliarEscape: // FamiliarEscape Msg
                // Empty
                break;
            case WaterSmashResult:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                break;
            case Unk88:
                outPacket.encodeByte(getArg1()); // type,
                switch (getArg1()) {
                    case 0:
                    case 2:
                    case 3:
                        outPacket.encodeInt(getArg2());
                        break;
                    case 1:
                    case 4:
                        outPacket.encodeInt(getArg2());
                        break;
                    case 5:
                        break;
                }
                break;
            case SomeUpgradeEffectOnUser:
                // Empty
                break;
        }
    }

    private void encodeSkillUse(OutPacket outPacket) {
        int skillID = getArg1();
        if (getUserEffectType() == SkillUseBySummoned) {
            outPacket.encodeInt(getArg4()); // Summon ID
        }
        outPacket.encodeInt(skillID); // Skill id
        outPacket.encodeInt(getArg2()); // chr Level
        outPacket.encodeInt(getArg3()); // slv
        if (skillID == Evan.DRAGON_FURY) { // Dragon Fury
            outPacket.encodeByte(getArg5()); // bCreate
        } else if (skillID == DarkKnight.FINAL_PACT_INFO) {
            outPacket.encodeByte(getArg5()); // bLoadReincarnationEffect
        } else if (skillID == BladeMaster.CHAINS_OF_HELL) {
            outPacket.encodeByte(getArg5()); // bLeft
            outPacket.encodeInt(getArg6()); // dwMobID
        } else if (skillID == 3211010 || skillID == 3111010 || skillID == 1100012) { // Hooks (Warrior combo fury/archer skills)
            outPacket.encodeByte(getArg5()); // bLeft
            outPacket.encodeInt(getArg6()); // dwMobID
            outPacket.encodeInt(getArg7()); // nMobPosX
            outPacket.encodeInt(getArg8()); // nMobPosY
        } else if (skillID == Cadena.CHAIN_ARTS_PURSUIT_HORIZONTAL_START || skillID == Cadena.CHAIN_ARTS_PURSUIT_UP_START
                || skillID == Cadena.CHAIN_ARTS_PURSUIT_DOWN_START) {
            outPacket.encodeByte(getArg5());
        } else if (skillID == Cadena.CHAIN_ARTS_PURSUIT_DOWN || skillID == Cadena.CHAIN_ARTS_PURSUIT_UP ||
                skillID == Cadena.CHAIN_ARTS_PURSUIT_HORIZONTAL) {
            outPacket.encodeByte(getArg5()); // isLeft
            outPacket.encodeInt(getArg7()); // dwMobID
            outPacket.encodeInt(getArg8()); // Chain End PosX
            outPacket.encodeInt(getArg9()); // Chain End PosY
        } else if (skillID == Cadena.CHAIN_ARTS_PURSUIT_PULL) {
            outPacket.encodeByte(getArg5()); // isLeft
            outPacket.encodeInt(getArg8()); // Chain End PosX
            outPacket.encodeInt(getArg9()); // Chain End PosY
            outPacket.encodeInt(getArg6()); // Origin Skill
        } else if (skillID == WildHunter.CALL_OF_THE_HUNTER) {
            outPacket.encodeByte(getArg5()); // bLeft
            outPacket.encodeShort(getArg6()); // nPosX
            outPacket.encodeShort(getArg7()); // nPosY
        } else if (skillID == WildHunter.CAPTURE) {
            outPacket.encodeByte(getArg5()); // nType: 0 = Success, 1 = mob hp too high, 2 = mob cannot be captured
        } else if (skillID == Kaiser.VERTICAL_GRAPPLE || skillID == AngelicBuster.GRAPPLING_HEART || skillID == Job.ROPE_LIFT) {
            // vertial grapples
            outPacket.encodeInt(getArg5()); // nStartPosY
            outPacket.encodeInt(getArg6()); // ptRopeConnectDest.x
            outPacket.encodeInt(getArg7()); // ptRopeConnectDest.y
        } else if (skillID == Luminous.FLASH_BLINK || skillID == 15001021 || skillID == Shade.FOX_TROT
                || skillID == Shadower.INTO_DARKNESS || skillID == 5081021 || skillID == Shadower.TRICKBLADE_FINISHER
                || skillID == Illium.CRYSTALLINE_WINGS) { // Flash, Vortex Jump
            outPacket.encodeInt(getArg5()); // ptBlinkLightOrigin.x
            outPacket.encodeInt(getArg6()); // ptBlinkLightOrigin.y
            outPacket.encodeInt(getArg7()); // ptBlinkLightDest.x
            outPacket.encodeInt(getArg8()); // ptBlinkLightDest.y
        } else if (SkillConstants.isSuperNovaSkill(skillID)) {
            outPacket.encodeInt(getArg5()); // ptStartX
            outPacket.encodeInt(getArg6()); // ptStartY
        } else if (SkillConstants.isSomeBlasterSkill(skillID)) {
            outPacket.encodeInt(getArg5());
        } else if (skillID == NightWalker.SHADOW_SPEAR_AA_LARGE) {
            outPacket.encodeInt(getArg5());
            outPacket.encodeInt(getArg6());
        } else if (skillID == Phantom.LUCK_OF_THE_DRAW) {
            outPacket.encodeInt(getArg5());
        } else if (SkillConstants.isLuckOfTheDrawSkill(skillID)) {
            outPacket.encodeInt(getArg5());
        } else if (skillID == Cadena.CHAIN_ARTS_VOID_STRIKE_ATTACK) {
            outPacket.encodeInt(getArg4());
            outPacket.encodeInt(getArg5());
            outPacket.encodeInt(getArg6());
            outPacket.encodeInt(getArg7());
            outPacket.encodeInt(getArg8());
            outPacket.encodeInt(getArg9());
            outPacket.encodeInt(getArg10());
            outPacket.encodeInt(getArg11());
        } else if (skillID == 80002393 || skillID == 80002394 || skillID == 80002395 || skillID == 80002421) {
            // Moonlight skills
            outPacket.encodeInt(getArg5());
        } else if (skillID == Pathfinder.CARDINAL_TORRENT || skillID == Pathfinder.CARDINAL_TORRENT_ADVANCED) {
            outPacket.encodeInt(getArg5());
        } else if (skillID == HoYoung.TALISMAN_EVIL_SEALING_GOURD_EFFECT) {
            outPacket.encodeInt(getArg5());
            outPacket.encodeInt(getArg6());
        } else if (skillID == 80001132) { // doesn't exist :thonk:
            outPacket.encodeByte(getArg5());
        } else if (SkillConstants.isUnregisteredSkill(skillID)) {
            outPacket.encodeByte(getArg5()); // bLeft
        } else if (skillID == Blaster.REVOLVING_CANNON_RELOAD) {
            outPacket.encodeInt(getArg5());
        }
    }

    public static Effect changeHPEffect(int hpDiff) {
        Effect effect = new Effect(IncDecHPEffect_EX);

        effect.setArg1(hpDiff);

        return effect;
    }


    public static Effect showTalismanSwallowEffect(int skillId, int chrLvl, int slv, int mobId, int templateId) {
        Effect effect = new Effect(SkillUse);

        effect.setArg1(skillId);
        effect.setArg2(chrLvl);
        effect.setArg3(slv);
        effect.setArg5(mobId);
        effect.setArg6(templateId);

        return effect;
    }


    public static Effect createFieldTextEffect(String msg, int letterDelay, int showTime, int clientPosition,
                                               Position boxPos, int align, int lineSpace, TextEffectType type,
                                               int enterType, int leaveType) {
        Effect effect = new Effect(TextEffect);

        effect.setString(msg);
        effect.setArg1(letterDelay);
        effect.setArg2(showTime);
        effect.setArg3(clientPosition);
        effect.setArg4(boxPos.getX());
        effect.setArg5(boxPos.getY());
        effect.setArg6(align);
        effect.setArg7(lineSpace);
        effect.setArg8(type.getVal());
        effect.setArg9(enterType);
        effect.setArg10(leaveType);

        return effect;
    }

    public static Effect skillPreLoopEnd(int skillId, int timeInMS) {
        Effect effect = new Effect(SkillPreLoopEnd);

        effect.setArg1(skillId);
        effect.setArg2(timeInMS);

        return effect;
    }

    public static Effect createABRechargeEffect() { // Angelic Buster's Recharge userEffect
        Effect effect = new Effect(ResetOnStateForOnOffSkill);

        return effect;
    }

    public static Effect fieldItemConsumed(int expGained) { // [exp]EXP+  effect from looting ExpOrbs
        Effect effect = new Effect(FieldExpItemConsumed);

        effect.setArg1(expGained);

        return effect;
    }

    public static Effect skillModeEffect(int skillID, int mode, int modeStatus) { // Unknown Effect
        Effect effect = new Effect(SkillMode);

        effect.setArg1(skillID);
        effect.setArg2(mode); //rotate
        effect.setArg3(modeStatus); //skip frame

        return effect;
    }

    public static Effect robbinsBombEffect(boolean reset, int bombCount, byte number) { //Displays bomb with [bombCount] on the Bomb, above user,   Unsure what '[int] number' does
        Effect effect = new Effect(RobbinsBomb);

        effect.setArg1(reset ? 1 : 0);  // if true, resets/delets bombs
        effect.setArg2(bombCount);      // Number displayed on the Bomb
        effect.setArg3(number);         // unknown

        return effect;
    }

    public static Effect lefMonsterNumberEffect(int count) { //Displays arrow pointing towards the user, with [count] on the arrow,  Maximum count is 5
        Effect effect = new Effect(LeftMonsterNumber);

        effect.setArg1(count); // number/counter on the arrow

        return effect;
    }

    public static Effect petBuffEffect() { //Displays the PetBuff Effect onto the user
        Effect effect = new Effect(PetBuff);

        return effect;
    }

    public static Effect jewelCraftResultEffect(byte result, int itemID) { // result = Displays Success/Fail effect above player head with smile/frown respectively, itemID will show itemName in chat
        Effect effect = new Effect(JewelCraft);

        effect.setArg1(result); // 0, 2  = Success/Smile,       5 = Request denied due to unk error,        Everything else = Fail/Frown
        effect.setArg2(itemID);

        return effect;
    }

    public static Effect showMobSkillHit(int skillId, int slv) {
        Effect effect = new Effect(MobSkillHit);

        effect.setArg1(skillId);
        effect.setArg2(slv);

        return effect;
    }

    /*
        public static Effect azwanSpearEffect(byte colour) { // 0 = red,    1 = orange
            Effect effect = new Effect();
            effect.setUserEffectType(AswanSiegeAttack);

            effect.setArg1(colour);

            return effect;
        }
    */
    public static Effect blindEffect(boolean active) {  // gives User the Blind Status Effect
        Effect effect = new Effect(BlindEffect);

        effect.setArg1(active ? 1 : 0);

        return effect;
    }

    public static Effect soulStoneUseEffect() { // gives the SoulStone Used Chat
        Effect effect = new Effect(SoulStoneUse);

        return effect;
    }

    public static Effect expItemConsumedEffect() { // gives exp item consumption effect
        Effect effect = new Effect(ExpItemConsumed);

        return effect;
    }

    public static Effect itemMakerEffect(boolean success) { // displays itemMaker result
        Effect effect = new Effect(ItemMaker);

        effect.setArg1(success ? 0 : 1); // 0 = Success,    1 = Failure

        return effect;
    }

    public static Effect itemLevelUpEffect() { //displays the Equipment Level Up effect
        Effect effect = new Effect(ItemLevelUp);

        return effect;
    }

    public static Effect questCompleteEffect() { //displays the Quest Complete effect
        Effect effect = new Effect(QuestComplete);

        return effect;
    }

    public static Effect incDecHPEffect(byte amount) { //displays the HP healing  effect
        Effect effect = new Effect(ItemLevelUp);

        effect.setArg1(amount); // amount shown being healed,  0 = miss

        return effect;
    }

    public static Effect changeJobEffect() { //displays the JobAdvancement Effect
        Effect effect = new Effect(JobChanged);

        return effect;
    }

    public static Effect buffItemEffect() { //displays the Buff Item Effect
        Effect effect = new Effect(BuffItemEffect);

        return effect;
    }

    public static Effect resistDamageEffect() { //displays the "Resist" Hit
        Effect effect = new Effect(Resist);

        return effect;
    }

    public static Effect levelUpEffect() { //displays the Level Up  Effect
        Effect effect = new Effect(LevelUp);

        return effect;
    }

    public static void showFameGradeUp(Char chr) {
        Field field = chr.getField();
        chr.write(UserPacket.effect(Effect.avatarOriented("Effect/BasicEff.img/FameGradeUp/front")));
        chr.write(UserPacket.effect(Effect.avatarOriented("Effect/BasicEff.img/FameGradeUp/back")));
        field.broadcastPacket(UserRemote.effect(chr.getId(), Effect.avatarOriented("Effect/BasicEff.img/FameGradeUp/front")));
        field.broadcastPacket(UserRemote.effect(chr.getId(), Effect.avatarOriented("Effect/BasicEff.img/FameGradeUp/back")));
    }

    public static Effect skillUse(int skillID, int chrLv, int slv, int bySummonedID) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(chrLv);
        effect.setArg3(slv);

        return effect;
    }

    public static Effect skillAffected(int skillID, int slv, int hpGain) {
        Effect effect = new Effect(SkillAffected);

        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(hpGain);

        return effect;
    }

    public static Effect skillAffectedSelect(int skillID, int slv, int select, boolean special) {
        return skillAffectedSelect(skillID, slv, select, -1, special);
    }

    public static Effect skillAffectedSelect(int skillID, int slv, int select, int rootSelect, boolean special) {
        Effect effect = new Effect(SkillAffected_Select);

        effect.setArg1(select);
        effect.setArg2(rootSelect);
        effect.setArg3(skillID);
        effect.setArg4(slv);
        effect.setArg5(special ? 1 : 0);

        return effect;
    }

    public static Effect skillSpecial(int skillID) {
        Effect effect = new Effect(SkillSpecial);

        effect.setArg1(skillID);

        return effect;
    }

    public static Effect skillSpecialAffected(int skillID, int slv) {
        Effect effect = new Effect(SkillSpecialAffected);

        effect.setArg1(skillID);
        effect.setArg2(slv);

        return effect;
    }

    public static Effect gainQuestItem(int itemID, int quantity) {
        Effect effect = new Effect(Quest);

        effect.setList(Collections.singletonList(new Tuple<>(itemID, quantity)));

        return effect;
    }

    public static Effect gainQuestItem(List<Tuple<Integer, Integer>> items) {
        Effect effect = new Effect(Quest);

        effect.setList(items);

        return effect;
    }

    public static Effect showChainArtPursuitEffect(int skillId, int chrLv, int slv, boolean isLeft, Position chainPos) {
        Effect effect = new Effect(SkillUse);

        effect.setArg1(skillId);
        effect.setArg2(chrLv);
        effect.setArg3(slv);
        effect.setArg5(isLeft ? 1 : 0);
        effect.setArg8(chainPos.getX());
        effect.setArg9(chainPos.getY());

        return effect;
    }

    public static Effect showChainArtPursuitPullEffect(int skillId, int chrLv, int slv, boolean isLeft, Position chainPos, int originSkill) {
        Effect effect = new Effect(SkillUse);

        effect.setArg1(skillId);
        effect.setArg2(chrLv);
        effect.setArg3(slv);
        effect.setArg5(isLeft ? 1 : 0);
        effect.setArg6(originSkill);
        effect.setArg8(chainPos.getX());
        effect.setArg9(chainPos.getY());

        return effect;
    }

    public static Effect showDragonFuryEffect(int skillID, int slv, int bySummonedID, boolean show) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(slv);
        effect.setArg5(show ? 1 : 0);

        return effect;
    }

    public static Effect showFinalPactEffect(int skillID, int slv, int bySummonedID, boolean show) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(slv);
        effect.setArg5(show ? 1 : 0);

        return effect;
    }

    public static Effect showChainsOfHellEffect(int skillID, int slv, int bySummonedID, boolean left, int mobId) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(slv);
        effect.setArg5(left ? 1 : 0);
        effect.setArg6(mobId);

        return effect;
    }

    public static Effect showHookEffect(int skillID, int chrLv, int slv, int bySummonedID, boolean left, int mobId, int mobPosX, int mobPosY) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(chrLv);
        effect.setArg3(slv);
        effect.setArg5(left ? 1 : 0);
        effect.setArg6(mobId);
        effect.setArg7(mobPosX);
        effect.setArg8(mobPosY);

        return effect;
    }

    public static Effect showCallOfTheHunterEffect(int skillID, int slv, int bySummonedID, boolean left, int mobPosX, int mobPosY) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(slv);
        effect.setArg5(left ? 1 : 0);
        effect.setArg6(mobPosX);
        effect.setArg7(mobPosY);

        return effect;
    }

    public static Effect showCaptureEffect(int skillID, int slv, int bySummonedID, int type) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(slv);
        effect.setArg5(type); // Type: 0 = Success,  1 = mob hp too high,  2 = mob cannot be captured

        return effect;
    }

    public static Effect showVerticalGrappleEffect(int skillID, int chrLv, int slv, int bySummonedID, int startPosY,
                                                   int ropeConnectDestX, int ropeConnectDestY) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(chrLv);
        effect.setArg3(slv);
        effect.setArg5(startPosY);
        effect.setArg6(ropeConnectDestX);
        effect.setArg7(ropeConnectDestY);

        return effect;
    }

    public static Effect showFlashBlinkEffect(int skillID, int slv, int bySummonedID, int blinkOriginX,
                                              int blinkOriginY, int blinkDestX, int blinkDestY) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(slv);
        effect.setArg5(blinkOriginX);
        effect.setArg6(blinkOriginY);
        effect.setArg7(blinkDestX);
        effect.setArg8(blinkDestY);

        return effect;
    }

    public static Effect showSuperNovaEffect(int skillID, int slv, int bySummonedID, int x, int y) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(slv);
        effect.setArg5(x);
        effect.setArg6(y);

        return effect;
    }

    public static Effect showUnregisteredSkill(int skillID, int slv, int bySummonedID, boolean left) {
        Effect effect = new Effect(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);

        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(slv);
        effect.setArg5(left ? 1 : 0);

        return effect;
    }

    public static Effect showDarkShockSkill(int skillId, int slv, Position origin, Position dest) {
        Effect effect = new Effect(SkillSpecial);

        effect.setArg1(skillId);
        effect.setArg2(slv);
        effect.setArg3(0); // show effect
        effect.setArg4(origin.getX());
        effect.setArg5(origin.getY());
        effect.setArg6(dest.getX());
        effect.setArg7(dest.getY());

        return effect;
    }

    public static Effect effectFromWZ(String effectPath, boolean flip, int duration, int type, int itemID) {
        Effect effect = new Effect(EffectUOL);

        effect.setString(effectPath);
        effect.setArg1(flip ? 1 : 0);
        effect.setArg2(duration);
        effect.setArg3(type);
        if (type == 2) {
            effect.setArg4(itemID);
        }

        return effect;
    }

    public static Effect fadeInOut(int fadeIn, int delay, int fadeOut, int alpha) {
        Effect effect = new Effect(FadeInOut);

        effect.setArg1(fadeIn);
        effect.setArg2(delay);
        effect.setArg3(fadeOut);
        effect.setArg4(alpha);
        return effect;
    }

    public static Effect avatarOriented(String effectPath) {
        Effect effect = new Effect(AvatarOriented);

        effect.setString(effectPath);

        return effect;
    }

    public static Effect reservedEffect(String effectPath) {
        Effect effect = new Effect(ReservedEffect);

        effect.setArg1(0);// bShow
        effect.setArg2(0);
        effect.setArg3(0);
        effect.setString(effectPath);

        return effect;
    }

    public static Effect reservedEffect(boolean screenCoord, int x, int y, String effectName) {
        Effect effect = new Effect(ReservedEffect);

        effect.arg1 = screenCoord ? 1 : 0;
        effect.arg2 = x;
        effect.arg3 = y;
        effect.string = effectName;

        return effect;
    }

    public static Effect reservedEffectRepeat(String effectPath, boolean start) {
        Effect effect = new Effect(ReservedEffectRepeat);

        effect.setString(effectPath);
        effect.setArg1(start ? 1 : 0);
        return effect;
    }

    public static Effect reservedEffectRepeat(String effectName, boolean idk, boolean show, int x, int y, int duration) {
        Effect effect = new Effect(ReservedEffectRepeat);

        effect.string = effectName;
        effect.arg1 = idk ? 1 : 0;
        effect.arg2 = duration;
        effect.arg3 = show ? 1 : 0;
        effect.arg4 = x;
        effect.arg5 = y;

        return effect;
    }

    public static Effect playExclSoundWithDownBGM(String soundPath, int volume) {
        Effect effect = new Effect(PlayExclSoundWithDownBGM);

        effect.setString(soundPath);
        effect.setArg1(volume);

        return effect;
    }
/*
*
                boolean normal = inPacket.decodeByte() != 0;
                int idx = inPacket.decodeInt();
                int linkType = inPacket.decodeInt();
                String speech = inPacket.decodeString();
                int time = inPacket.decodeInt();
                int align = inPacket.decodeInt();
                int x = inPacket.decodeInt();
                int y = inPacket.decodeInt();
                int z = inPacket.decodeInt();
                int lineSpace = inPacket.decodeInt();
                int npcTemplateId = inPacket.decodeInt();*/

    public static Effect speechBalloon(boolean normal, int idx, int linkType, String speech, int time, int align, int x,
                                       int y, int z, int lineSpace, int npcTemplateId, int idk) {
        Effect effect = new Effect(SpeechBalloon);

        effect.arg1 = normal ? 1 : 0;
        effect.arg2 = idx;
        effect.arg3 = linkType;
        effect.string = speech;
        effect.arg4 = time;
        effect.arg5 = align;
        effect.arg6 = x;
        effect.arg7 = y;
        effect.arg8 = z;
        effect.arg9 = lineSpace;
        effect.arg10 = npcTemplateId;
        effect.arg11 = idk;

        return effect;
    }

    public Effect(UserEffectType userEffectType) {
        this.userEffectType = userEffectType;
    }

    public Effect() {
    }

    public void setUserEffectType(UserEffectType userEffectType) {
        this.userEffectType = userEffectType;
    }

    public UserEffectType getUserEffectType() {
        return userEffectType;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setList(List<Tuple<Integer, Integer>> list) {
        this.list = list;
    }

    public List<Tuple<Integer, Integer>> getList() {
        return list;
    }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public int getArg3() {
        return arg3;
    }

    public void setArg3(int arg3) {
        this.arg3 = arg3;
    }

    public int getArg4() {
        return arg4;
    }

    public void setArg4(int arg4) {
        this.arg4 = arg4;
    }

    public int getArg5() {
        return arg5;
    }

    public void setArg5(int arg5) {
        this.arg5 = arg5;
    }

    public int getArg6() {
        return arg6;
    }

    public void setArg6(int arg6) {
        this.arg6 = arg6;
    }

    public int getArg7() {
        return arg7;
    }

    public void setArg7(int arg7) {
        this.arg7 = arg7;
    }

    public int getArg8() {
        return arg8;
    }

    public void setArg8(int arg8) {
        this.arg8 = arg8;
    }

    public int getArg9() {
        return arg9;
    }

    public void setArg9(int arg9) {
        this.arg9 = arg9;
    }

    public int getArg10() {
        return arg10;
    }

    public void setArg10(int arg10) {
        this.arg10 = arg10;
    }

    public int getArg11() {
        return arg11;
    }

    public void setArg11(int arg11) {
        this.arg11 = arg11;
    }
}
