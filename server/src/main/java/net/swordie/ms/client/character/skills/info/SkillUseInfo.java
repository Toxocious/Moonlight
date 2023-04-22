    package net.swordie.ms.client.character.skills.info;

    import net.swordie.ms.util.Position;
    import net.swordie.ms.util.Rect;
    import net.swordie.ms.util.container.Tuple;

    import java.util.Set;

    /**
     * Created on 21-8-2019.
     *
     * @author Asura
     */
    public class SkillUseInfo {
        // General
        public int skillId;
        public int objectId;
        public Position position;
        public Position endingPosition;
        public boolean isLeft;
        public Rect rect;

        // Projectile
        public int projectileItemId;

        // Rock 'N Shock (Mechanic)
        public byte rockNshockSize;
        public int[] rockNshockLifes = new int[2]; // used on last rockNshock, client encodes 2 previous rockNshocks planted in the Field.

        // Adele Shardbreaker
        public boolean spawnCrystals;
        public Set<Tuple<Integer, Position>> shardsPositions;
    }
