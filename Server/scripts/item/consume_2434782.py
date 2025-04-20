# White Bonus Potential Fragment

FRAGMENT = 2434782
QUANTITY = 10
REWARD = 2048307 # 100% bonus potential scroll

q = sm.getQuantityOfItem(FRAGMENT)

if q >= QUANTITY:
	if sm.canHold(REWARD):
		sm.giveItem(REWARD)
		sm.consumeItem(FRAGMENT, QUANTITY)
	else:
		sm.systemMessage("Make sure you have enough space in your inventory..")
else:
	sm.systemMessage("One must have at least " + str(QUANTITY) + " fragments to unleash the magic powers..")