field = chr.getField()
instance = chr.getInstance()
init = instance.initialised

if not init:
    instance.initialised = True
    mob = sm.spawnMob(9410248, -373, 123, False)