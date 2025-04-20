STATE_LENGTH = [156, 146, 136, 115, 105, 95, 84, 43, 33, 0]
BIGBY_REACTOR = 1301000
BIGBY_MOB = 9001051
EGG = 4033196

if sm.hasQuest(20033):
    if sm.getReactorState(BIGBY_REACTOR) < 10:
        sm.dropItem(EGG, sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY())
        sm.increaseReactorState(BIGBY_REACTOR, STATE_LENGTH[sm.getReactorState(BIGBY_REACTOR)])
        sm.removeReactor()
        if sm.getReactorState(BIGBY_REACTOR) == 10:
            sm.systemMessage("Bigby's leash has torn into two.")
            sm.spawnMob(BIGBY_MOB, 229, 65, False)
    sm.dispose()
        
        