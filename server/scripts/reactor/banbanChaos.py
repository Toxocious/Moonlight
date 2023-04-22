chaosVonBon = 8910000
if chr.getInstance() is not None and reactor.getHitCount() == 0:
    sm.spawnMob(chaosVonBon, -135, 455, False)
    reactor.incHitCount()
    sm.removeReactor()