from net.swordie.ms.constants import BossConstants

spawned = field.hasProperty(BossConstants.RANMARU_SPAWN_KEY) and field.getProperty(BossConstants.RANMARU_SPAWN_KEY)
if not spawned:
    field.setProperty(BossConstants.RANMARU_SPAWN_KEY, True)
    sm.spawnMob(9421583, -373, 123, False, BossConstants.RANMARU_HP)