package net.swordie.ms.world.field.bosses.gollux;

import net.swordie.ms.util.Position;

import java.util.List;

/**
 * Created on 4-3-2019.
 *
 * @author Asura
 */
public class FallingCatcher {
    private String templateStr;
    private int hpR;
    private List<Position> positions;

    public FallingCatcher(String templateStr, int hpR, List<Position> position) {
        this.templateStr = templateStr;
        this.hpR = hpR;
        this.positions = position;
    }

    public String getTemplateStr() {
        return templateStr;
    }

    public void setTemplateStr(String templateStr) {
        this.templateStr = templateStr;
    }

    public int getHpR() {
        return hpR;
    }

    public void setHpR(int hpR) {
        this.hpR = hpR;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public void addPosition(Position position) {
        getPositions().add(position);
    }
}
