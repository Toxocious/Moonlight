package net.swordie.ms.client.character;

import net.swordie.ms.client.character.cards.CharacterCard;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.util.SystemTime;

import net.swordie.ms.util.FileTime;

import javax.persistence.*;

/**
 * Created by Tim on 2/18/2017.
 */
@Entity
@Table(name = "characterstats")
public class CharacterStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "characterId")
    private int characterId;
    private int characterIdForLog;
    private int worldIdForLog;
    private String name;
    private int gender;
    private int skin;
    private int face;
    private int hair;
    private int mixBaseHairColor;
    private int mixAddHairColor;
    private int mixHairBaseProb;
    private int level;
    private int job;
    private int str;
    private int dex;
    private int inte;
    private int luk;
    private int hp;
    private int maxHp;
    private int mp;
    private int maxMp;
    private int ap;
    private int sp;
    private long exp;
    private int pop; // fame
    private long money;
    private int wp;
    private double pierce;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "extendSP")
    private ExtendSP extendSP;
    private long posMap;
    private int portal;
    private int subJob;
    private int defFaceAcc;
    private int fatigue;
    private int lastFatigueUpdateTime;
    private int charismaExp;
    private int insightExp;
    private int willExp;
    private int craftExp;
    private int senseExp;
    private int charmExp;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "nonCombatStatDayLimit")
    private NonCombatStatDayLimit nonCombatStatDayLimit;
    private int pvpExp;
    private int pvpGrade;
    private int pvpPoint;
    private int pvpModeLevel;
    private int pvpModeType;
    private int eventPoint;
    private int albaActivityID;
    @Convert(converter = FileTimeConverter.class)
    private FileTime albaStartTime;
    private int albaDuration;
    private int albaSpecialReward;
    private boolean burning;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "characterCard")
    private CharacterCard characterCard;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "accountLastLogout")
    private SystemTime accountLastLogout;
    @Convert(converter = FileTimeConverter.class)
    private FileTime lastLogout;
    private int gachExp;
    private int honorExp;
    @Transient
    private int wingItem;
    @Convert(converter = FileTimeConverter.class)
    private FileTime nextAvailableFameTime;

    public CharacterStat() {
        extendSP = new ExtendSP(7);
        nonCombatStatDayLimit = new NonCombatStatDayLimit();
        albaStartTime = FileTime.fromType(FileTime.Type.PLAIN_ZERO);
        lastLogout = FileTime.fromType(FileTime.Type.PLAIN_ZERO);
        characterCard = new CharacterCard(0, 0, (byte) 0);
        accountLastLogout = new SystemTime(1970, 1);
        nextAvailableFameTime = FileTime.fromType(FileTime.Type.PLAIN_ZERO);
        // TODO fill in default vals
    }

    public CharacterStat(String name, int job) {
        this();
        this.name = name;
        this.job = job;
    }

    public double getPierce() {
        return pierce;
    }

    public void gainPierce(double pierce) {
        if (this.pierce + pierce >= 100.00) {
            this.pierce = 100.00;
        } else this.pierce += pierce;
    }

    public void setPierce(double pierce) {
        if (pierce >= 100.00)
            this.pierce = 100.0;
        else this.pierce = pierce;
    }

    public String getName() {
        return name;
    }

    public short getAp() {
        return (short) ap;
    }

    public short getDex() {
        return (short) dex;
    }

    public int getHp() {
        return hp;
    }

    public short getInt() {
        return (short) inte;
    }

    public short getJob() {
        return (short) job;
    }

    public short getLevel() {
        return (short) level;
    }

    public short getCharismaExp() {
        return (short) charismaExp;
    }

    public short getLuk() {
        return (short) luk;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public int getMp() {
        return mp;
    }

    public short getPop() { //Fame
        return (short) pop;
    }

    public short getSp() {
        return (short) sp;
    }

    public short getStr() {
        return (short) str;
    }

    public short getWp() {
        return (short) wp;
    }

    public long getExp() {
        return exp;
    }

    public long getMoney() {
        return money;
    }

    public ExtendSP getExtendSP() {
        return extendSP;
    }

    public int getCharacterId() {
        return characterId;
    }

    public int getCharacterIdForLog() {
        return characterId;
    }

    public int getFace() {
        return face;
    }

    public int getGender() {
        return gender;
    }

    public int getHair() {
        return hair;
    }

    public int getMixAddHairColor() {
        return mixAddHairColor;
    }

    public int getMixBaseHairColor() {
        return mixBaseHairColor;
    }

    public int getMixHairBaseProb() {
        return mixHairBaseProb;
    }

    public int getSkin() {
        return skin;
    }

    public int getWorldIdForLog() {
        return worldIdForLog;
    }

    public short getCharmExp() {
        return (short) charmExp;
    }

    public short getCraftExp() {
        return (short) craftExp;
    }

    public int getAlbaActivityID() {
        return albaActivityID;
    }

    public int getEventPoint() {
        return eventPoint;
    }

    public int getPortal() {
        return portal;
    }

    public int getAlbaDuration() {
        return albaDuration;
    }

    public short getInsightExp() {
        return (short) insightExp;
    }

    public int getAlbaSpecialReward() {
        return albaSpecialReward;
    }

    public int getPvpExp() {
        return pvpExp;
    }

    public int getPvpGrade() {
        return pvpGrade;
    }

    public int getPvpModeLevel() {
        return pvpModeLevel;
    }

    public int getPvpModeType() {
        return pvpModeType;
    }

    public int getPvpPoint() {
        return pvpPoint;
    }

    public short getSenseExp() {
        return (short) senseExp;
    }

    public short getWillExp() {
        return (short) willExp;
    }

    public long getPosMap() {
        return posMap == 0 ? 931000000 : posMap;
    }

    public CharacterCard getCharacterCard() {
        return characterCard;
    }

    public NonCombatStatDayLimit getNonCombatStatDayLimit() {
        return nonCombatStatDayLimit;
    }

    public FileTime getAlbaStartTime() {
        return albaStartTime;
    }

    public int getDefFaceAcc() {
        return defFaceAcc;
    }

    public int getFatigue() {
        return fatigue;
    }

    public int getLastFatigueUpdateTime() {
        return lastFatigueUpdateTime;
    }

    public int getSubJob() {
        return subJob;
    }

    public SystemTime getAccountLastLogout() {
        return accountLastLogout;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getCharacterId());
        outPacket.encodeInt(getCharacterIdForLog());
        outPacket.encodeInt(getWorldIdForLog());
        outPacket.encodeString(getName(), 13);

        outPacket.encodeByte(getGender());
        outPacket.encodeByte(getSkin());
        outPacket.encodeInt(getFace());
        outPacket.encodeInt(getHair());
        outPacket.encodeByte(getMixBaseHairColor());
        outPacket.encodeByte(getMixAddHairColor());
        outPacket.encodeByte(getMixHairBaseProb());

        outPacket.encodeInt(getLevel());
        outPacket.encodeShort(getJob());
        outPacket.encodeShort(getStr());
        outPacket.encodeShort(getDex());
        outPacket.encodeShort(getInt());
        outPacket.encodeShort(getLuk());
        outPacket.encodeInt(getHp());
        outPacket.encodeInt(getMaxHp());
        outPacket.encodeInt(getMp());
        outPacket.encodeInt(getMaxMp());
        outPacket.encodeShort(getAp());
        if (JobConstants.isExtendSpJob(getJob())) {
            getExtendSP().encode(outPacket);
        } else {
            outPacket.encodeShort(getSp());
        }
        outPacket.encodeLong(getExp());
        outPacket.encodeInt(getPop());
        outPacket.encodeInt(getWp()); // Waru
        outPacket.encodeInt(getGachExp());
        outPacket.encodeInt((int) getPosMap());
        outPacket.encodeByte(getPortal());
        outPacket.encodeInt(0); // TODO figure out
        outPacket.encodeShort(getSubJob());
        if (JobConstants.isDemon(getJob()) || JobConstants.isXenon(getJob()) || JobConstants.isBeastTamer(getJob())
                || JobConstants.isArk(getJob()) || JobConstants.isHoYoung(getJob())) { // new check for hoyoung v210
            outPacket.encodeInt(getDefFaceAcc());
        }
        outPacket.encodeShort(getFatigue()); // short in 188
        outPacket.encodeInt(getLastFatigueUpdateTime());
        outPacket.encodeInt(getCharismaExp());
        outPacket.encodeInt(getInsightExp());
        outPacket.encodeInt(getWillExp());
        outPacket.encodeInt(getCraftExp());
        outPacket.encodeInt(getSenseExp());
        outPacket.encodeInt(getCharmExp());
        getNonCombatStatDayLimit().encode(outPacket);

        outPacket.encodeInt(getPvpExp());
        outPacket.encodeByte(getPvpGrade());
        outPacket.encodeInt(getPvpPoint());
        outPacket.encodeByte(2);
        /* Fuck that, setting the above byte lower than 2 will make all 3rd and 4th job that have the property
         ((skillID % 10000) / 10000 == 0) be bugged (you see the level, but can't actually use it). ?????????????*/

        outPacket.encodeByte(getPvpModeType());
        outPacket.encodeInt(getEventPoint());

        // New - 188 - looks like char card
        // Start sub_8D7900
        for (int i = 0; i < 9; i++) {
            outPacket.encodeInt(1);
            outPacket.encodeInt(2); // byte -> int v199
            outPacket.encodeInt(3);
        }
        // End sub_8D7900
        outPacket.encodeInt(3);
        outPacket.encodeInt(5);
        // Start burning info, names are guesses
        outPacket.encodeLong(0); // ftStart
        outPacket.encodeLong(0); // ftEnd
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeByte(isBurning()); // bBurning
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        // End burning info
        // End new 188
        outPacket.encodeByte(false); // new 200
    }

    public FileTime getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(FileTime lastLogout) {
        this.lastLogout = lastLogout;
    }

    public boolean isBurning() {
        return burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGachExp() {
        return gachExp;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public void setCharacterIdForLog(int characterIdForLog) {
        this.characterIdForLog = characterIdForLog;
    }

    public void setWorldIdForLog(int worldIdForLog) {
        this.worldIdForLog = worldIdForLog;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setSkin(int skin) {
        this.skin = skin;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public void setHair(int hair) {
        this.hair = hair;
    }

    public void setMixAddHairColor(int mixAddHairColor) {
        this.mixAddHairColor = mixAddHairColor;
    }

    public void setMixHairBaseProb(int mixHairBaseProb) {
        this.mixHairBaseProb = mixHairBaseProb;
    }

    public void setMixBaseHairColor(int mixBaseHairColor) {
        this.mixBaseHairColor = mixBaseHairColor;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setInt(int inte) {
        this.inte = inte;
    }

    public void setLuk(int luk) {
        this.luk = luk;
    }

    public void setHp(int hp) {
        this.hp = Math.min(hp, GameConstants.MAX_HP_MP);
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = Math.min(maxHp, GameConstants.MAX_HP_MP);
    }

    public void setMp(int mp) {
        this.mp = Math.min(mp, GameConstants.MAX_HP_MP);
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = Math.min(maxMp, GameConstants.MAX_HP_MP);
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setWp(int wp) {
        this.wp = wp;
    }

    public void setPosMap(long posMap) {
        this.posMap = posMap;
    }

    public void setPortal(int portal) {
        this.portal = portal;
    }

    public void setSubJob(int subJob) {
        this.subJob = subJob;
    }

    public void setDefFaceAcc(int defFaceAcc) {
        this.defFaceAcc = defFaceAcc;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public void setLastFatigueUpdateTime(int lastFatigueUpdateTime) {
        this.lastFatigueUpdateTime = lastFatigueUpdateTime;
    }

    public void setCharismaExp(int charismaExp) {
        this.charismaExp = charismaExp;
    }

    public void setInsightExp(int insightExp) {
        this.insightExp = insightExp;
    }

    public void setWillExp(int willExp) {
        this.willExp = willExp;
    }

    public void setCraftExp(int craftExp) {
        this.craftExp = craftExp;
    }

    public void setSenseExp(int senseExp) {
        this.senseExp = senseExp;
    }

    public void setCharmExp(int charmExp) {
        this.charmExp = charmExp;
    }

    public void setPvpExp(int pvpExp) {
        this.pvpExp = pvpExp;
    }

    public void setPvpGrade(int pvpGrade) {
        this.pvpGrade = pvpGrade;
    }

    public void setPvpPoint(int pvpPoint) {
        this.pvpPoint = pvpPoint;
    }

    public void setPvpModeLevel(int pvpModeLevel) {
        this.pvpModeLevel = pvpModeLevel;
    }

    public void setPvpModeType(int pvpModeType) {
        this.pvpModeType = pvpModeType;
    }

    public void setEventPoint(int eventPoint) {
        this.eventPoint = eventPoint;
    }

    public void setAlbaActivityID(int albaActivityID) {
        this.albaActivityID = albaActivityID;
    }

    public void setAlbaDuration(int albaDuration) {
        this.albaDuration = albaDuration;
    }

    public void setAlbaSpecialReward(int albaSpecialReward) {
        this.albaSpecialReward = albaSpecialReward;
    }

    public void setGachExp(int gachExp) {
        this.gachExp = gachExp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExtendSP(ExtendSP extendSP) {
        this.extendSP = extendSP;
    }

    public void setNonCombatStatDayLimit(NonCombatStatDayLimit nonCombatStatDayLimit) {
        this.nonCombatStatDayLimit = nonCombatStatDayLimit;
    }

    public void setAlbaStartTime(FileTime albaStartTime) {
        this.albaStartTime = albaStartTime;
    }

    public void setCharacterCard(CharacterCard characterCard) {
        this.characterCard = characterCard;
    }

    public void setAccountLastLogout(SystemTime accountLastLogout) {
        this.accountLastLogout = accountLastLogout;
    }

    public int getHonorExp() {
        return honorExp;
    }

    public void setHonorExp(int honorExp) {
        this.honorExp = honorExp;
    }

    public void setWingItem(int wingItem) {
        this.wingItem = wingItem;
    }

    public int getWingItem() {
        return wingItem;
    }

    public FileTime getNextAvailableFameTime() {
        return nextAvailableFameTime;
    }

    public void setNextAvailableFameTime(FileTime nextAvailableFameTime) {
        this.nextAvailableFameTime = nextAvailableFameTime;
    }
}

