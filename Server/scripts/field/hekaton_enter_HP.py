from net.swordie.ms.constants import BossConstants
from net.swordie.ms.enums import ObtacleAtomEnum

sm.invokeAtFixedRate(250, BossConstants.HEKATON_RED_ATOM_EXECUTION_DELAY, 0,
                     "createObstacleAtom", ObtacleAtomEnum.HekatonRedOrb, 1, BossConstants.HEKATON_RED_ATOM_DAMAGE, BossConstants.HEKATON_OBSTACLE_ATOM_VELOCITY, BossConstants.HEKATON_RED_ATOM_AMOUNT, BossConstants.HEKATON_RED_ATOM_PROP)
