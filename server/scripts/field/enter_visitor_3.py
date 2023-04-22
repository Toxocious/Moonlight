if sm.hasFieldProperty("Visitor3"):
    sm.dispose()
else:
    sm.setFieldProperty("Visitor3", True)
    sm.spawnMob(9390110, 267, 32, False, 4000000000000)