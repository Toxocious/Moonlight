# To close the door in Lord Pirate PQ
OLD_METAL_KEY = 4001117
hitCount = 0

reactor.incHitCount()
if reactor.getHitCount() >= 1:
    if sm.hasItem(OLD_METAL_KEY):
        sm.consumeItem(OLD_METAL_KEY)
        sm.removeReactor()
    sm.dispose()
