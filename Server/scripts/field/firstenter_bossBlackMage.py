# 450,013,100 - Limina: Temple of Darkness
TIME = 30 * 60

MAP_P1 = 450013100
MAP_P2 = 450013300
MAP_P3 = 450013500
MAP_P4 = 450013700
MAP_DIFF = 200 # 100 if direction maps are included

AEONIAN_RISE = 8880500
TANADIAN_RUIN = 8880501
BLACK_MAGE_P2 = 8880502
BLACK_MAGE_P3 = 8880503
BLACK_MAGE_P4 = 8880504



fieldId = field.getId()

if fieldId == MAP_P1:
    sm.setInstanceTime(TIME)
    sm.spawnMob(AEONIAN_RISE, -942, 85, 32500000000000) # 32.5t
    sm.spawnMob(TANADIAN_RUIN, 942, 85, 32500000000000) # 32.5t

    sm.waitForMobDeath(AEONIAN_RISE, TANADIAN_RUIN)
    sm.waitForMobDeath(AEONIAN_RISE, TANADIAN_RUIN)

    sm.warpField(MAP_P2)
elif fieldId == MAP_P2:
    sm.spawnMob(BLACK_MAGE_P2, 0, 88, 135000000000000) # 135t
    sm.waitForMobDeath(BLACK_MAGE_P2)

    sm.warpField(MAP_P3)
elif fieldId == MAP_P3:
    sm.spawnMob(BLACK_MAGE_P3, 375, 88, 200000000000000) # 200t
    sm.waitForMobDeath(BLACK_MAGE_P3)

    sm.warpField(MAP_P4)
elif fieldId == MAP_P4:
    sm.spawnMob(BLACK_MAGE_P4, 375, 88, 100000000000000) # 100t

