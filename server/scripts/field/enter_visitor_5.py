if sm.hasFieldProperty("Visitor5"):
    sm.dispose()
else:
    sm.setFieldProperty("Visitor5", True)
    sm.spawnMob(9430020, 309, 32, False, 6000000000000)