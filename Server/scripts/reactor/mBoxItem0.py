# Maple Road - Inside the Dangerous Forest/South Perry Reactor
import random

# Reactor Tutorial Maple Island Explorer
DANGEROUS_FOREST = 4000014
FIRST_EXPLORER_GIFT_BOX = 4033915

# Maple Island Explorer Storyline
SOUTH_PERRY = 4000030
FLUSTERED_EXPLORER = 32213
BOAT_BOARDING_TICKET = 4033914

SMALL_FOREST = 912060100
KINDLING = 4032980  # Quest Item for Cannoneer Intro
FIREWORRD_FOR_THE_BONFIRE_QUEST = 2570

reactor.incHitCount()
reactor.increaseState()

if reactor.getHitCount() < 4:
    sm.dispose()

x, y = sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY()

if sm.getFieldID() == DANGEROUS_FOREST:
    sm.dropItem(FIRST_EXPLORER_GIFT_BOX, x, y)
elif sm.getFieldID() == SOUTH_PERRY and sm.hasQuest(FLUSTERED_EXPLORER) and not sm.hasItem(BOAT_BOARDING_TICKET):
    sm.dropItem(BOAT_BOARDING_TICKET, x, y)
elif sm.getFieldID() == SMALL_FOREST and sm.hasQuest(FIREWORRD_FOR_THE_BONFIRE_QUEST) and not sm.hasItem(
        BOAT_BOARDING_TICKET):
    sm.dropItem(KINDLING, x, y)
else:
    # Get random value against 10 - 100
    meso = random.randint(10, 500)
    chr.getField().dropMeso(meso, x, y, x, y + 20)
    sm.dropItem(2010009, x, y)

sm.removeReactor()
sm.dispose()
