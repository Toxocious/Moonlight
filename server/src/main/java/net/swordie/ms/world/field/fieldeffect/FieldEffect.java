package net.swordie.ms.world.field.fieldeffect;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.mob.Mob;

/**
 * Created on 3/26/2018.
 */
public class FieldEffect {

    private FieldEffectType fieldEffectType;
    private String string;
    private String string2;
    private String string3;
    private int arg1;
    private int arg2;
    private int arg3;
    private int arg4;
    private int arg5;
    private int arg6;
    private long arg7;
    private long arg8;
    private int arg9;

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getFieldEffectType().getVal());
        switch (getFieldEffectType()) {
            case Summon:
                outPacket.encodeByte(getArg1()); // type
                outPacket.encodeInt(getArg2()); // x
                outPacket.encodeInt(getArg3()); // y
                break;
            case Tremble:
                outPacket.encodeByte(getArg1()); // bHeavyNShortTremble
                outPacket.encodeInt(getArg2()); // tDelay
                outPacket.encodeShort(getArg3()); // dTrembleForce
                break;
            case Object:
                outPacket.encodeString(getString());// String
                System.out.println(getString());
                break;
            case ObjectDisable:
                outPacket.encodeString(getString());// String
                outPacket.encodeByte(getArg1());    // boolean: ON/OFF
                break;
            case Screen:
                outPacket.encodeString(getString());// String
                break;
            case PlaySound:
                outPacket.encodeString(getString()); // Sound
                outPacket.encodeInt(getArg1()); // Volume
                break;
            case MobHPTag:
                outPacket.encodeInt(getArg1());     // Mob Template ID
                outPacket.encodeLong(getArg7());    // Mob HP
                outPacket.encodeLong(getArg8());    // Mob max HP
                outPacket.encodeByte(getArg4());    // HP Tag Colour
                outPacket.encodeByte(getArg5());    // HP Tab BG Colour
                break;
            case ChangeBGM:
                outPacket.encodeString(getString());// sDir
                outPacket.encodeInt(getArg1());     // idk
                outPacket.encodeInt(getArg2());     // tStartTime
                break;
            case BGMVolumeOnly:
                outPacket.encodeByte(getArg1() != 0); // bBGMVolumeOnly
                break;
            case BGMVolume:
                outPacket.encodeInt(getArg1());     // nVolume
                outPacket.encodeInt(getArg2());     // nFadingDuration
                break;
            case Unk10:
            case Unk11:
            case Unk13:
                outPacket.encodeInt(getArg1());
                break;
            case Unk12:
            case Unk14:
                break;
            case RewardRoulette:
                outPacket.encodeInt(getArg1());     // Reward Job ID
                outPacket.encodeInt(getArg2());     // Reward Part ID
                outPacket.encodeInt(getArg3());     // Reward Level ID
                break;
            case TopScreen:
                outPacket.encodeString(getString());// Directory to the Effect
                break;
            case ScreenDelayed:
                outPacket.encodeString(getString());// Directory to the Effect
                outPacket.encodeInt(getArg1());     // Delay in ms
                break;
            case TopScreenDelayed:                   // Goes over other effects
                outPacket.encodeString(getString());// Directory to the Effect
                outPacket.encodeInt(getArg1());     // Delay in ms
                break;
            case ScreenAutoLetterBox:
                outPacket.encodeString(getString());// Path to the Effect
                outPacket.encodeInt(getArg1());     // Delay in ms
                break;
            case FloatingUI:
                outPacket.encodeString(getString());
                outPacket.encodeByte(getArg1()); // enumOrigin
                outPacket.encodeByte(getArg2()); // bRepeat
                break;
            case Blind:
                outPacket.encodeByte(getArg1());
                outPacket.encodeShort(getArg2());
                outPacket.encodeShort(getArg3());
                outPacket.encodeShort(getArg4());
                outPacket.encodeShort(getArg5());
                outPacket.encodeInt(getArg6());
                outPacket.encodeInt((int) getArg7()); // new int
                break;
            case GreyScale:
                outPacket.encodeShort(getArg1());   // GreyField Type
                outPacket.encodeByte(getArg2());    // boolean: ON/OFF
                break;
            case OnOffLayer:
                outPacket.encodeByte(getArg1());    // nType (0 = On, 1 = Move, 2 = Off)
                outPacket.encodeInt(getArg2());     // tDuration
                outPacket.encodeString(getString());// sKey
                if (getArg1() == 0) {
                    outPacket.encodeInt(getArg3()); // nRX
                    outPacket.encodeInt(getArg4()); // nRY
                    outPacket.encodeInt(getArg5()); // nZ
                    outPacket.encodeString(getString2()); // pOrigin (eg. "Effect/~.img/~/0")
                    outPacket.encodeInt(getArg6()); // nOrigin
                    outPacket.encodeByte((byte) getArg7()); // bPostRender
                    outPacket.encodeInt(getArg9()); // idk
                    outPacket.encodeByte((byte) getArg8()); // bRepeat?
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                } else if (getArg1() == 1) {
                    outPacket.encodeInt(getArg3()); // nDX
                    outPacket.encodeInt(getArg4()); // nDY
                } else if (getArg1() == 2) {
                    outPacket.encodeByte((byte) getArg8()); // ?
                } else if (getArg1() == 3) {
                    outPacket.encodeString(getString2()); // pOrigin?
                    outPacket.encodeInt(getArg3()); // nDX?
                    outPacket.encodeInt(getArg4()); // nDY?
                }
                break;
            case Overlap:                    // Takes a Snapshot of the Client and slowly fades away
                outPacket.encodeInt(getArg1());     // Duration of the overlap (ms)
                break;
            case OverlapDetail:
                outPacket.encodeInt(getArg1());     // Fade In
                outPacket.encodeInt(getArg2());     // wait time
                outPacket.encodeInt(getArg3());     // Fade Out
                outPacket.encodeByte(getArg4());    // some boolean
                break;
            case RemoveOverlapDetail:
                outPacket.encodeInt(getArg1());     // Fade Out duration
                break;
            case ColorChange:
                outPacket.encodeShort(getArg1());   // GreyField Type (but doesn't contain Reactor
                outPacket.encodeShort(getArg2());   // red      (255 is normal value)
                outPacket.encodeShort(getArg3());   // green    (255 is normal value)
                outPacket.encodeShort(getArg4());   // blue     (255 is normal value)
                outPacket.encodeInt(getArg5());     // time in ms, that it takes to transition from old colours to the new colours
                outPacket.encodeInt(0);          // is in queue
                if (getArg1() == 4) {// Npc
                    outPacket.encodeInt(getArg6()); // Npc Id (?)
                }
                break;
            case StageClear:
                outPacket.encodeInt(getArg1());     // Exp Number given
                break;
            case TopScreenWithOrigin:
                outPacket.encodeString(getString());// Directory to the Effect
                outPacket.encodeByte(getArg1());    // enumOrigin
                break;
            case SpineScreen:
                outPacket.encodeByte(getArg1()); // bBinary
                outPacket.encodeByte(getArg2()); // bLoop
                outPacket.encodeByte(getArg3()); // bPostRender
                outPacket.encodeInt(getArg4()); // tEndDelay
                outPacket.encodeString(getString()); // sPath
                outPacket.encodeString(getString2()); // sAnimationName
                outPacket.encodeString(""); // ?
                outPacket.encodeByte(false); // ?
                outPacket.encodeInt(0); // ?
                outPacket.encodeInt(0); // ?
                outPacket.encodeInt(0); // ?
                outPacket.encodeInt(0); // ?
                boolean hasKey = getString3() != null && !"".equals(getString3());
                outPacket.encodeByte(hasKey);
                if (hasKey) {
                    outPacket.encodeString(getString3()); // sKeyName
                }
                break;
            case OffSpineScreen:
                outPacket.encodeString(getString()); // sKeyName
                outPacket.encodeInt(getArg1()); // nType
                if (getArg1() == 1) {
                    outPacket.encodeInt(getArg2()); // tAlpha
                } else if (getArg1() == 2) {
                    outPacket.encodeString(getString2()); // sAnimationName
                }
                break;
            case Unk33:
                outPacket.encodeString(getString());
                if (getString() != null && !getString().isEmpty()) {
                    outPacket.encodeByte(getArg1());
                    outPacket.encodeString(getString2());
                    if (getArg1() == 19) {
                        outPacket.encodeInt(getArg2());
                        // v181 = 0, v182 = 1, v183 = arg2
                    } else if (getArg1() == 17) {
                        outPacket.encodeInt(getArg2());
                        // v181 = arg2, v182 = 0, v183 = 0
                    }
                }
                break;
            case Unk34:
                outPacket.encodeString(getString());
                break;
            case Unk35:
                outPacket.encodeInt(getArg1());
                outPacket.encodeString(getString());
                if (getString() != null && !getString().isEmpty()) {
                    if (getArg1() == 0) {
                        outPacket.encodeInt(getArg2());
                        outPacket.encodeInt(getArg3());
                        outPacket.encodeInt(getArg4());
                        outPacket.encodeInt(getArg5());
                        outPacket.encodeString(getString2());
                        outPacket.encodeInt(getArg6());
                        outPacket.encodeByte(getArg7() != 0);
                        outPacket.encodeInt((int) getArg8());
                        outPacket.encodeByte(getArg9());
                        outPacket.encodeInt(0);
                        outPacket.encodeInt(0);
                    } else if (getArg1() == 1) {
                        outPacket.encodeInt(getArg2());
                    } else if (getArg1() == 2) {
                        outPacket.encodeInt(getArg2());
                        outPacket.encodeInt(getArg3());
                        outPacket.encodeInt(getArg4());
                        outPacket.encodeInt(getArg5());
                    }
                }
                break;
            case Unk36:
                outPacket.encodeString(getString()); // Sound/%s?
                break;
            case Unk37:
                outPacket.encodeByte(getArg1());
                outPacket.encodeShort(getArg2());
                outPacket.encodeShort(getArg3());
                outPacket.encodeShort(getArg4());
                outPacket.encodeShort(getArg5());
                outPacket.encodeInt(getArg6());
                outPacket.encodeByte(0);
                break;
        }
    }

    public static FieldEffect mobHPTagFieldEffect(Mob mob) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.MobHPTag);

        fieldEffect.setArg1(mob.getTemplateId());
        fieldEffect.setArg7(mob.getHp());
        fieldEffect.setArg8(mob.getMaxHp());
        fieldEffect.setArg4(mob.getHpTagColor());
        fieldEffect.setArg5(mob.getHpTagBgcolor());

        return fieldEffect;
    }

    public static FieldEffect changeBGM(String dir, int startTime, int idk) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.ChangeBGM);

        fieldEffect.setString(dir);
        fieldEffect.setArg1(startTime);
        fieldEffect.setArg2(idk);

        return fieldEffect;
    }

    public static FieldEffect bgmVolumeOnly(boolean volumeOnly) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.BGMVolumeOnly);

        fieldEffect.setArg1(volumeOnly ? 1 : 0);

        return fieldEffect;
    }

    public static FieldEffect bgmVolume(int volume, int fadingDuration) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.BGMVolume);

        fieldEffect.setArg1(volume);
        fieldEffect.setArg2(fadingDuration);

        return fieldEffect;
    }

    public static FieldEffect getFieldEffectFromWz(String dir, int delay) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.ScreenAutoLetterBox);

        fieldEffect.setString(dir);
        fieldEffect.setArg1(delay);

        return fieldEffect;
    }

    public static FieldEffect getFieldEffectFromObject(String dir) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.Object);
        fieldEffect.setString(dir);

        return fieldEffect;
    }

    public static FieldEffect getFieldBackgroundEffectFromWz(String dir, int delay) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.ScreenDelayed);

        fieldEffect.setString(dir);
        fieldEffect.setArg1(delay);

        return fieldEffect;
    }

    public static FieldEffect getOffFieldEffectFromWz(String dir, int delay) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.TopScreenDelayed);

        fieldEffect.setString(dir);
        fieldEffect.setArg1(delay);

        return fieldEffect;
    }

    public static FieldEffect setFieldGrey(GreyFieldType greyFieldType, boolean setGrey) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.GreyScale);

        fieldEffect.setArg1(greyFieldType.getVal());
        fieldEffect.setArg2(setGrey ? 1 : 0);

        return fieldEffect;
    }

    public static FieldEffect setFieldColor(GreyFieldType colorFieldType, short red, short green, short blue, int time) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.ColorChange);

        fieldEffect.setArg1(colorFieldType.getVal());
        fieldEffect.setArg2(red);
        fieldEffect.setArg3(green);
        fieldEffect.setArg4(blue);
        fieldEffect.setArg5(time);

        return fieldEffect;
    }

    public static FieldEffect showClearStageExpWindow(int expNumber) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.StageClear);

        fieldEffect.setArg1(expNumber);

        return fieldEffect;
    }

    public static FieldEffect takeSnapShotOfClient(int duration) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.Overlap);

        fieldEffect.setArg1(duration);

        return fieldEffect;
    }

    public static FieldEffect onOffLayer(int type, int duration, String key, int x, int y, int z, String origin, int org, boolean postRender, int idk, boolean repeat) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.OnOffLayer);

        fieldEffect.setArg1(type);
        fieldEffect.setArg2(duration);
        fieldEffect.setString(key);
        fieldEffect.setArg3(x);
        fieldEffect.setArg4(y);
        fieldEffect.setArg5(z);
        fieldEffect.setString2(origin);
        fieldEffect.setArg6(org);
        fieldEffect.setArg7(postRender ? 1 : 0);
        fieldEffect.setArg9(idk);
        fieldEffect.setArg8(repeat ? 1 : 0); // unsure if it's repeat

        return fieldEffect;
    }

    public static FieldEffect takeSnapShotOfClient2(int transitionDurationToSnapShot, int inBetweenDuration, int transitionBack, boolean someBoolean) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.OverlapDetail);

        fieldEffect.setArg1(transitionDurationToSnapShot);
        fieldEffect.setArg2(inBetweenDuration);
        fieldEffect.setArg3(transitionBack);
        fieldEffect.setArg4(someBoolean ? 1 : 0);

        return fieldEffect;
    }

    public static FieldEffect removeOverlapScreen(int duration) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.RemoveOverlapDetail);

        fieldEffect.setArg1(duration);

        return fieldEffect;
    }

    public static FieldEffect playSound(String sound, int vol) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.PlaySound);

        fieldEffect.setString(sound);
        fieldEffect.setArg1(vol);

        return fieldEffect;
    }

    public static FieldEffect blind(int enable, int x, int color, int unk1, int unk2, int time) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.Blind);

        fieldEffect.setArg1(enable);
        fieldEffect.setArg2(x);
        fieldEffect.setArg3(color);
        fieldEffect.setArg4(unk1);
        fieldEffect.setArg5(unk2);
        fieldEffect.setArg6(time);

        return fieldEffect;
    }

    public static FieldEffect spineScreen(boolean binary, boolean loop, boolean postRender, int endDelay, String path,
                                          String animationName, String keyName) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.SpineScreen);

        fieldEffect.arg1 = binary ? 1 : 0;
        fieldEffect.arg2 = loop ? 1 : 0;
        fieldEffect.arg3 = postRender ? 1 : 0;
        fieldEffect.arg4 = endDelay;
        fieldEffect.string = path;
        fieldEffect.string2 = animationName;
        fieldEffect.string3 = keyName;

        return fieldEffect;
    }

    public static FieldEffect offSpineScreen(String keyName, int type, String aniName, int alphaDecayTime) {
        FieldEffect fieldEffect = new FieldEffect(FieldEffectType.OffSpineScreen);

        fieldEffect.string = keyName;
        fieldEffect.arg1 = type;
        fieldEffect.string2 = aniName;
        fieldEffect.arg2 = alphaDecayTime;

        return fieldEffect;
    }

    public FieldEffect(FieldEffectType fieldEffectType) {
        this.fieldEffectType = fieldEffectType;
    }

    public FieldEffect() {
    }

    public FieldEffectType getFieldEffectType() {
        return fieldEffectType;
    }

    public void setFieldEffectType(FieldEffectType fieldEffectType) {
        this.fieldEffectType = fieldEffectType;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
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

    public long getArg7() {
        return arg7;
    }

    public void setArg7(long arg7) {
        this.arg7 = arg7;
    }

    public long getArg8() {
        return arg8;
    }

    public void setArg8(long arg8) {
        this.arg8 = arg8;
    }

    public int getArg9() {
        return arg9;
    }

    public void setArg9(int arg9) {
        this.arg9 = arg9;
    }
}
