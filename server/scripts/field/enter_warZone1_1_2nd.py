# Occupied reclamation HQ  |  Used for Magnus prequest
from net.swordie.ms.constants import WzConstants


sm.showEffect(WzConstants.EFFECT_MONSTER_PARK_FINAL_STAGE)
sm.spawnMob(2400109, -540, 182, False)
sm.spawnMob(2400109, -348, 182, False)
sm.spawnMob(2400109, -191, 182, False)
sm.spawnMob(2400109, -95, 182, False)
sm.spawnMob(2400109, 172, 182, False)
sm.spawnMob(2400109, 346, 182, False)
sm.spawnMob(2400109, 554, 182, False)
sm.spawnMob(2400109, 691, 182, False)
sm.spawnMob(2400109, 829, 182, False)
sm.spawnMob(2400109, 895, 182, False)
sm.spawnMob(2400109, 1073, 182, False)
sm.spawnMob(2400109, 1266, 182, False)
sm.spawnMob(2400109, -414, -118, False)
sm.spawnMob(2400109, -279, -118, False)
sm.spawnMob(2400109, -87, -118, False)
sm.spawnMob(2400109, 94, -487, False)
sm.spawnMob(2400109, 115, -487, False)
sm.spawnMob(2400109, 178, -487, False)
sm.spawnMob(2400109, 381, -487, False)
sm.spawnMob(2400109, 603, -487, False)
sm.spawnMob(2400109, 752, -487, False)
sm.spawnMob(2400018, 1181, -178, False)

if sm.getChr().getField().getMobs().size() == 0:
    sm.showEffect(WzConstants.EFFECT_MONSTER_PARK_CLEAR)


