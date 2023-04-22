from net.swordie.ms.constants import BossConstants
from net.swordie.ms.enums import ObtacleAtomEnum

sm.spawnMob(8880008, 1448, -1347, False, 10000000000)
sm.setDeathCount(5)
sm.setInstanceTime(BossConstants.MAGNUS_TIME)


sm.invokeAtFixedRate(250, BossConstants.MAGNUS_GREEN_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.GreenMeteor, 1, BossConstants.MAGNUS_GREEN_ATOM_DAMAGE, BossConstants.MAGNUS_OBSTACLE_ATOM_VELOCITY, BossConstants.MAGNUS_GREEN_ATOM_AMOUNT, BossConstants.MAGNUS_GREEN_ATOM_PROP)

sm.invokeAtFixedRate(500, BossConstants.MAGNUS_BLUE_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.BlueMeteor, 1, BossConstants.MAGNUS_BLUE_ATOM_DAMAGE, BossConstants.MAGNUS_OBSTACLE_ATOM_VELOCITY, BossConstants.MAGNUS_BLUE_ATOM_AMOUNT, BossConstants.MAGNUS_BLUE_ATOM_PROP)