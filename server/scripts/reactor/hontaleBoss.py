from net.swordie.ms.scripts import ScriptType

reactor.incHitCount()
reactor.increaseState()
if reactor.getHitCount() == 4:
    sm.removeReactor()
    sm.startScript(0, "HorntailFight", ScriptType.Field)