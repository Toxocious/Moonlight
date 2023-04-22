
if chr.getInstance() is not None and reactor.getHitCount() == 0:
    # global hitCount
    # hitCount += 1
    # sm.chat(str(hitCount))
    # if hitCount >= 1:
    reactor.incHitCount()
    sm.spawnMob(9303154, -135, 455, False)
    sm.removeReactor()
    sm.dispose()
