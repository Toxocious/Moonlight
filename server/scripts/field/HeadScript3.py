if sm.getFieldID() == 240060002:
    if not sm.hasFieldProperty("HeadSpawned"):
        sm.setFieldProperty("HeadSpawned", True)
        sm.spawnMob(8810000, 868, 230, False)
if sm.getFieldID() == 240060101:
    if not sm.hasFieldProperty("SecondHeadSpawned"):
        sm.setFieldProperty("SecondHeadSpawned", True)
        sm.spawnMob(8810101, -317,230, False)