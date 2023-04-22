# Occupied reclamation HQ  |  Used for Magnus prequest
from net.swordie.ms.constants import WzConstants


sm.showFieldEffect("Map/Effect.img/monsterPark/stageEff/stage")
sm.showFieldEffect("Map/Effect.img/monsterPark/stageEff/number/1")
sm.spawnMob(2400109, -552, 182, False)
sm.spawnMob(2400109, -296, 182, False)
sm.spawnMob(2400109, -187, 182, False)
sm.spawnMob(2400109, -129, 182, False)
sm.spawnMob(2400109, 93, 182, False)
sm.spawnMob(2400109, 346, 182, False)
sm.spawnMob(2400109, 478, 182, False)
sm.spawnMob(2400109, 649, 182, False)
sm.spawnMob(2400109, 804, 182, False)
sm.spawnMob(2400109, 863, 182, False)
sm.spawnMob(2400109, 870, 182, False)
sm.spawnMob(2400109, 920, 182, False)
sm.spawnMob(2400109, 1000, 182, False)
sm.spawnMob(2400109, 1075, 182, False)
sm.spawnMob(2400109, 1120, 182, False)
sm.spawnMob(2400109, 1180, 182, False)
sm.spawnMob(2400109, 1220, 182, False)
sm.spawnMob(2400109, 1275, 182, False)
sm.spawnMob(2400109, 1300, 182, False)
sm.spawnMob(2400109, 1352, 182, False)
if sm.getChr().getField().getMobs().size() == 0:
    sm.showEffect(WzConstants.EFFECT_MONSTER_PARK_CLEAR)


