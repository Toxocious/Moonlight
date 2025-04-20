# Black Heaven Inside: Core (350060200)  |  Stage 3 Lotus Boss  |  Used to spawn the Boss
from net.swordie.ms.constants import BossConstants
from net.swordie.ms.enums import ObtacleAtomEnum

LOTUS = 8240105 # Stage 3

sm.spawnMob(LOTUS, 0, -16, False)


sm.invokeAtFixedRate(0, BossConstants.LOTUS_BLUE_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.LotusBlueDebris, 1, BossConstants.LOTUS_BLUE_ATOM_DAMAGE, BossConstants.LOTUS_OBSTACLE_ATOM_VELOCITY, BossConstants.LOTUS_BLUE_ATOM_AMOUNT, BossConstants.LOTUS_BLUE_ATOM_PROP)

sm.invokeAtFixedRate(250, BossConstants.LOTUS_YELLOW_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.LotusYellowDebris, 2, BossConstants.LOTUS_YELLOW_ATOM_DAMAGE, BossConstants.LOTUS_OBSTACLE_ATOM_VELOCITY, BossConstants.LOTUS_YELLOW_ATOM_AMOUNT, BossConstants.LOTUS_YELLOW_ATOM_PROP)

sm.invokeAtFixedRate(500, BossConstants.LOTUS_PURPLE_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.LotusPurpleDebris, 3, BossConstants.LOTUS_PURPLE_ATOM_DAMAGE, BossConstants.LOTUS_OBSTACLE_ATOM_VELOCITY, BossConstants.LOTUS_PURPLE_ATOM_AMOUNT, BossConstants.LOTUS_PURPLE_ATOM_PROP)

sm.invokeAtFixedRate(1000, BossConstants.LOTUS_ROBOT_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.LotusRobotDebris, 4, BossConstants.LOTUS_ROBOT_ATOM_DAMAGE, BossConstants.LOTUS_OBSTACLE_ATOM_VELOCITY, BossConstants.LOTUS_ROBOT_ATOM_AMOUNT, BossConstants.LOTUS_ROBOT_ATOM_PROP)

sm.invokeAtFixedRate(2000, BossConstants.LOTUS_CRUSHER_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.LotusCrusherDebris, 5, BossConstants.LOTUS_CRUSHER_ATOM_DAMAGE, BossConstants.LOTUS_OBSTACLE_ATOM_VELOCITY, BossConstants.LOTUS_CRUSHER_ATOM_AMOUNT, BossConstants.LOTUS_CRUSHER_ATOM_PROP)
