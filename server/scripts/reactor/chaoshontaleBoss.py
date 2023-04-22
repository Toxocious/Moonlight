from net.swordie.ms.scripts import ScriptType

reactor.incHitCount()
reactor.increaseState()
if reactor.getHitCount() >= 4:
    sm.removeReactor()
    sm.startScript(0, "HorntailFight", ScriptType.Field)
    sm.spawnMob(8810102, 95, 260, False)
    sm.spawnMob(8810103, 95, 260, False)
    sm.spawnMob(8810104, 95, 260, False)
    sm.spawnMob(8810105, 95, 260, False)
    sm.spawnMob(8810106, 95, 260, False)
    sm.spawnMob(8810107, 95, 260, False)
    sm.spawnMob(8810108, 95, 260, False)
    sm.spawnMob(8810109, 95, 260, False)
    sm.removeReactor()