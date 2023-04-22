package net.swordie.ms.client.character.skills.temp;

/**
 * Created on 2/3/2018.
 */
public class TwoStateTemporaryStat extends TemporaryStatBase {

    public TwoStateTemporaryStat(boolean dynamicTermSet) {
        super(dynamicTermSet);
    }

    @Override
    public int getMaxValue() {
        return 0;
    }

    @Override
    public boolean isActive() {
        return getNOption() != 0;
    }

}
