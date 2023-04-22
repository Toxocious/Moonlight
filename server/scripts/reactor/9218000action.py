# Prison Door (9218000) | Used in Escape Party Quest

PRISON_KEY = 4001528

reactor.incHitCount()
if reactor.getHitCount() >= 1:
    if sm.hasItem(PRISON_KEY):
        sm.consumeItem(PRISON_KEY)
        sm.removeReactor()
    else:
        sm.chat("You need a key in order to open the Prison.")
    sm.dispose()
