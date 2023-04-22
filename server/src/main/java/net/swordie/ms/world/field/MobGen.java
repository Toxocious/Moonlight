package net.swordie.ms.world.field;

import net.swordie.ms.connection.packet.MobPool;
import net.swordie.ms.constants.CustomConstants;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.MobConstants;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.util.Position;

/**
 * @author Sjonnie
 * Created on 7/26/2018.
 */
public class MobGen extends Life {

    private Mob mob;
    private long nextPossibleSpawnTime = Long.MIN_VALUE;
    private boolean hasSpawned;

    public MobGen(int templateId) {
        super(templateId);
    }

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
        this.setPosition(mob.getHomePosition().deepCopy());
    }

    /**
     * Spawns a Mob at the position of this MobGen.
     */
    public void spawnMob(Field field, boolean buffed) {
        Mob mob = getMob().deepCopy();
        if (!field.getNoRespawn().contains(mob.getTemplateId())) {
        if (buffed && !mob.isBoss()) {
            int prefix = field.getId() / 1000000;
            mob.buff(MobConstants.getBuffMultiplierFromRegion(prefix));
        }        
        Position pos = mob.getHomePosition();
        mob.setPosition(pos.deepCopy());
        mob.setHomePosition(pos.deepCopy());
        field.spawnLife(mob, null);
        if (CustomConstants.AUTO_AGGRO) {
            field.broadcastPacket(MobPool.forceChase(mob.getObjectId(), false));
        }
        setNextPossibleSpawnTime(System.currentTimeMillis() + (getMob().getMobTime() * 1000));
        setHasSpawned(true);
        }
    }

    public MobGen deepCopy() {
        MobGen mobGen = new MobGen(getTemplateId());
        if (getMob() != null) {
            mobGen.setMob(getMob().deepCopy());
        }
        return mobGen;
    }

    public boolean canSpawnOnField(Field field) {
        int currentMobs = field.getMobs().size();
        // not over max mobs, delay of spawn ended, if mobtime == -1 (not respawnable) must not have yet spawned
        // no mob in area around this, unless kishin is active
        return currentMobs < field.getMobCapacity() &&
                getNextPossibleSpawnTime() < System.currentTimeMillis() &&
                (getMob().getMobTime() != -1 || !hasSpawned()) &&
                (field.hasKishin() ||
                        field.getMobsInRect(getPosition().getRectAround(GameConstants.MOB_CHECK_RECT)).size() == 0);
    }

    public long getNextPossibleSpawnTime() {
        return nextPossibleSpawnTime;
    }

    public void setNextPossibleSpawnTime(long nextPossibleSpawnTime) {
        this.nextPossibleSpawnTime = nextPossibleSpawnTime;
    }

    public boolean hasSpawned() {
        return hasSpawned;
    }

    public void setHasSpawned(boolean hasSpawned) {
        this.hasSpawned = hasSpawned;
    }
}
