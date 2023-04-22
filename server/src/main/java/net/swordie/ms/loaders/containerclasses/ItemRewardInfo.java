package net.swordie.ms.loaders.containerclasses;

/**
 * @author Sjonnie
 * Created on 7/21/2018.
 */
public class ItemRewardInfo {
    private int count;
    private int itemID;
    private double prob;
    private int period;
    private String effect = "";

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setProb(double prob) {
        this.prob = prob;
    }

    public double getProb() {
        return prob;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }
}
