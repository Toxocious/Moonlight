# Black Cube Fragment

REWARD = 5062009 # red cube
QUANTITY = 10
FRAGMENT = 2431894

q = sm.getQuantityOfItem(FRAGMENT)

if q >= QUANTITY:
	if sm.canHold(REWARD):
		sm.giveItem(REWARD)
		sm.consumeItem(FRAGMENT, QUANTITY)
	else:
		sm.systemMessage("Make sure you have enough space in your inventory..")
else:
	sm.systemMessage("One must have at least " + str(QUANTITY) + " fragments to unleash the magic powers..")