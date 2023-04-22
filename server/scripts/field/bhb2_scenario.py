# Black Heaven Inside: Core (350060180)  |  Stage 2 Lotus Boss  |  Used to spawn the Boss
from net.swordie.ms.constants import BossConstants
from net.swordie.ms.enums import ObtacleAtomEnum

LOTUS = 8240104 # Stage 2

sm.spawnMob(LOTUS, 0, -16, False)


sm.invokeAtFixedRate(0, BossConstants.LOTUS_BLUE_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.LotusBlueDebris, 1, BossConstants.LOTUS_BLUE_ATOM_DAMAGE, BossConstants.LOTUS_OBSTACLE_ATOM_VELOCITY, BossConstants.LOTUS_BLUE_ATOM_AMOUNT, BossConstants.LOTUS_BLUE_ATOM_PROP)

sm.invokeAtFixedRate(250, BossConstants.LOTUS_YELLOW_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.LotusYellowDebris, 2, BossConstants.LOTUS_YELLOW_ATOM_DAMAGE, BossConstants.LOTUS_OBSTACLE_ATOM_VELOCITY, BossConstants.LOTUS_YELLOW_ATOM_AMOUNT, BossConstants.LOTUS_YELLOW_ATOM_PROP)

sm.invokeAtFixedRate(500, BossConstants.LOTUS_PURPLE_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.LotusPurpleDebris, 3, BossConstants.LOTUS_PURPLE_ATOM_DAMAGE, BossConstants.LOTUS_OBSTACLE_ATOM_VELOCITY, BossConstants.LOTUS_PURPLE_ATOM_AMOUNT, BossConstants.LOTUS_PURPLE_ATOM_PROP)
