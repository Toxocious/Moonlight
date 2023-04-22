# Key Chest (921160600) | Drops a key on the map | Used in Escape Party Quest
PRISON_KEY = 4001528

reactor.incHitCount()
if reactor.getHitCount() >= 1:
    sm.dropItem(PRISON_KEY, sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY())
    sm.removeReactor()
    sm.dispose()
