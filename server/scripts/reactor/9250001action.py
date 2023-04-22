# Flower | Primrose Hill
STAGE_1_CLEARED = "Stage1Cleared"
STAGE_1_FLOWER_DATA = "Stage1FlowerData"

sm.increaseReactorState(9250001, 1)
sm.consumeItem(4000884)

field.setProperty(STAGE_1_FLOWER_DATA, field.getProperty(STAGE_1_FLOWER_DATA) + 1)

if field.getProperty(STAGE_1_FLOWER_DATA) == 6:
    field.setProperty(STAGE_1_CLEARED, True)
    field.clearRespawn()
    mobids = [9300900, 9300901]
    field.setNoRespawn(mobids) 
    sm.killMobs(); 
    sm.spawnMob(9300902, -198, -189, False)
    sm.spawnMob(9300907, -198, -189, False)    
    sm.chat("Stage cleared")