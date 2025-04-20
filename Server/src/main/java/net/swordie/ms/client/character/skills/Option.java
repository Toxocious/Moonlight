package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.life.Summon;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created on 1/3/2018.
 */
public class Option {
    private static final Random keyRng = new Random();
    public int nOption;
    public int rOption;
    public int tOption;
    public int xOption;
    public int mOption;
    public int wOption;
    public int uOption;
    public int zOption;
    public int bOption;
    public int sOption;
    public int ssOption;
    public int cOption;
    public int yOption;
    public int nReason;
    public int nValue;
    public int nKey = keyRng.nextInt();
    public int tStart;
    public int tTerm;
    public int pOption;
    public int slv;
    public long startTime = Util.getCurrentTimeLong();
    public Summon summon;
    public Char chr;
    public List<Option> extraOpts = new ArrayList<>();
    public boolean isInMillis = false;

    public Option(int skillID) {
        this.nReason = skillID;
        this.rOption = skillID;
        this.tStart = Util.getCurrentTime();
    }

    public Option(int itemID, long duration) {
        // hack to have a constructor for items
        this.tTerm = (int) duration;
        this.nReason = itemID;
        this.rOption = itemID;
        this.tOption = (int) duration;
        this.tStart = Util.getCurrentTime();
    }

    public Option(int skillID, int slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        rOption = skillID;
        tOption = si.getValue(SkillStat.time, slv);
        this.tStart = Util.getCurrentTime();
    }

    public Option() {
        tStart = Util.getCurrentTime();
    }

    public Option deepCopy() {
        Option copy = new Option();
        copy.nOption = nOption;
        copy.rOption = rOption;
        copy.tOption = tOption;
        copy.xOption = xOption;
        copy.mOption = mOption;
        copy.wOption = wOption;
        copy.uOption = uOption;
        copy.zOption = zOption;
        copy.bOption = bOption;
        copy.sOption = sOption;
        copy.ssOption = ssOption;
        copy.cOption = cOption;
        copy.pOption = pOption;
        copy.yOption = yOption;
        copy.nReason = nReason;
        copy.nValue = nValue;
        copy.nKey = nKey;
        copy.tStart = tStart;
        copy.tTerm = tTerm;
        copy.slv = slv;
        copy.summon = summon;
        extraOpts.forEach(o -> extraOpts.add(o.deepCopy()));
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return rOption == option.rOption &&
                nReason == option.nReason;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rOption, nReason);
    }

    @Override
    public String toString() {
        if (nReason == 0) {
            return "Indie: false, skill: " + rOption + ", val: " + nOption + ", time: " + tOption;
        } else {
            return "Indie: true, skill: " + nReason + ", val: " + nValue + ", time: " + tTerm;
        }
    }

    public boolean isInMillis() {
        return isInMillis;
    }

    public void setInMillis(boolean inMillis) {
        isInMillis = inMillis;
    }

    /**
     * Sets the time options of this Option to milliseconds, if they currently aren't. By default, an Option's time
     * is in seconds.
     */
    public void setTimeToMillis() {
        if (!isInMillis) {
            tTerm *= 1000;
            tOption *= 1000;
            isInMillis = true;
        }
    }


}
