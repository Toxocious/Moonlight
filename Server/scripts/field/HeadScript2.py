if sm.getFieldID() == 240060001:
    if not sm.hasFieldProperty("HeadSpawned"):
        sm.setFieldProperty("HeadSpawned", True)
        sm.spawnMob(8810100, 868, 230, False)
if sm.getFieldID() == 240060100:
    if not sm.hasFieldProperty("SecondHeadSpawned"):
        sm.setFieldProperty("SecondHeadSpawned", True)
        sm.spawnMob(8810001, -317,230, False)
