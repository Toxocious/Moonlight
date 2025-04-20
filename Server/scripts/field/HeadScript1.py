if sm.getFieldID() == 240060000:
    if not sm.hasFieldProperty("HeadSpawned"):
        sm.setFieldProperty("HeadSpawned", True)
        sm.spawnMob(8810200, 868, 230, False)
else:
    if not sm.hasFieldProperty("SecondHeadSpawned"):
        sm.setFieldProperty("SecondHeadSpawned", True)
        sm.spawnMob(8810001, -317,230, False)
