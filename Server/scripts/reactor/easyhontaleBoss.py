from net.swordie.ms.scripts import ScriptType

reactor.incHitCount()
reactor.increaseState()
if reactor.getHitCount() >= 4:
    sm.removeReactor()
    sm.startScript(0, "HorntailFight", ScriptType.Field)
    sm.spawnMob(8810202, 95, 260, False)
    sm.spawnMob(8810203, 95, 260, False)
    sm.spawnMob(8810204, 95, 260, False)
    sm.spawnMob(8810205, 95, 260, False)
    sm.spawnMob(8810206, 95, 260, False)
    sm.spawnMob(8810207, 95, 260, False)
    sm.spawnMob(8810208, 95, 260, False)
    sm.spawnMob(8810209, 95, 260, False)
    sm.removeReactor()