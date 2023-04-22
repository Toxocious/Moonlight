# Super Memory Cube Fragment

REWARD = 5062000 # miracle cube
QUANTITY = 10
FRAGMENT = 2430481

q = sm.getQuantityOfItem(FRAGMENT)

if q >= QUANTITY:
	if sm.canHold(REWARD):
		sm.giveItem(REWARD)
		sm.consumeItem(FRAGMENT, QUANTITY)
	else:
		sm.systemMessage("Make sure you have enough space in your inventory..")
else:
	sm.systemMessage("One must have at least " + str(QUANTITY) + " fragments to unleash the magic powers..")