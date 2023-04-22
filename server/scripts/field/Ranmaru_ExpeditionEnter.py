from net.swordie.ms.constants import BossConstants

field = chr.getField()
instance = chr.getInstance()
init = instance.initialised

if not init:
    instance.initialised = True
    mob = sm.spawnMob(BossConstants.RANMARU_CHAOS_TEMPLATE_ID, -373, 123, False, BossConstants.RANMARU_CHAOS_HP)