reactor = sm.getReactor(objectID)
if reactor is not None and sm.hasQuest(22502):
    reactor.incHitCount()
    if reactor.getHitCount() >= 5:
        sm.dropItem(4032452,sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY())
        reactor.setHitCount(0)
        sm.dispose()