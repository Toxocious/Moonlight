package net.swordie.ms.client.character.items;

import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.EnchantStat;
import net.swordie.ms.enums.EquipBaseStat;
import net.swordie.ms.enums.SpellTraceScrollType;
import net.swordie.ms.util.Util;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Sjonnie
 * Created on 8/18/2018.
 */
public class ScrollUpgradeInfo implements Encodable {
    private int iconID;
    private String title;
    private SpellTraceScrollType type;
    private int option;
    private TreeMap<EnchantStat, Integer> stats; // needs to be sorted
    private int cost;
    private int chance;
    private int oldCost;

    public ScrollUpgradeInfo(int iconID, String title, SpellTraceScrollType scrollType, int scrollOption,
                             TreeMap<EnchantStat, Integer> scrollStats, int cost, int chance) {
        this.iconID = iconID;
        this.title = title;
        this.type = scrollType;
        this.option = scrollOption;
        this.stats = scrollStats;
        this.cost = cost;
        this.chance = chance;
    }

    public int getMask() {
        int mask = 0;
        for (EnchantStat es : getStats().keySet()) {
            mask |= es.getVal();
        }
        return mask;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getIconID());
        outPacket.encodeString(getTitle());
        outPacket.encodeInt(getType().ordinal());
        outPacket.encodeInt(getOption());
        outPacket.encodeInt(getMask());
        for (Map.Entry<EnchantStat, Integer> entry : getStats().entrySet()) {
            outPacket.encodeInt(entry.getValue());
        }
        outPacket.encodeInt(getOldCost());
        outPacket.encodeInt(getCost());
        outPacket.encodeByte(0); // ignored?
    }

    public boolean applyTo(Equip equip) {
        boolean success = false;
        switch (getType()) {
            case Normal:
                int chance = getChance();
                if (equip.hasAttribute(EquipAttribute.LuckyDay)) {
                    chance += 10;
                }
                success = Util.succeedProp(chance);
                if (success) {
                    for (Map.Entry<EnchantStat, Integer> entry : getStats().entrySet()) {
                        EnchantStat es = entry.getKey();
                        int val = entry.getValue();
                        equip.addStat(es.getEquipBaseStat(), val);
                    }
                    equip.addStat(EquipBaseStat.tuc, -1);
                    equip.addStat(EquipBaseStat.cuc, 1);
                } else {
                    if (!equip.hasAttribute(EquipAttribute.UpgradeCountProtection)) {
                        equip.addStat(EquipBaseStat.tuc, -1);
                    }
                }
                equip.removeAttribute(EquipAttribute.LuckyDay);
                equip.removeAttribute(EquipAttribute.UpgradeCountProtection);
                break;
            case CleanSlate:
                success = Util.succeedProp(getChance());
                if (success) {
                    equip.addStat(EquipBaseStat.tuc, 1);
                }
                break;
            case Innocence:
                success = Util.succeedProp(getChance());
                if (success) {
                    equip.applyInnocenceScroll();
                }
                break;
        }
        return success;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SpellTraceScrollType getType() {
        return type;
    }

    public void setType(SpellTraceScrollType type) {
        this.type = type;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public TreeMap<EnchantStat, Integer> getStats() {
        return stats;
    }

    public void setStats(TreeMap<EnchantStat, Integer> stats) {
        this.stats = stats;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getOldCost() {
        return oldCost;
    }

    public void setOldCost(int oldCost) {
        this.oldCost = oldCost;
    }
}
