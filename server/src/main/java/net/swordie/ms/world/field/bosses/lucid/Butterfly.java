package net.swordie.ms.world.field.bosses.lucid;

import net.swordie.ms.util.Position;

/**
 * Created on 20-1-2019.
 *
 * @author Asura
 */
public class Butterfly {
    ButterflyType type;
    Position position;

    public Butterfly(ButterflyType type, Position position) {
        this.type = type;
        this.position = position;
    }

    public ButterflyType getType() {
        return type;
    }

    public void setType(ButterflyType type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
