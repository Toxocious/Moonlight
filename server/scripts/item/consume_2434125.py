# Violet Cube Fragment

FRAGMENT = 2434125
REWARD1 = 5062009 # red cube
REWARD2 = 5062010 # black cube

q = sm.getQuantityOfItem(FRAGMENT)

if q >= 10:
	if sm.canHold(REWARD2):
		sm.giveItem(REWARD2)
		sm.consumeItem(FRAGMENT, 10)
	else:
		sm.systemMessage("Make sure you have enough space in your inventory..")
elif q >= 5:
	if sm.canHold(REWARD1):
		sm.giveItem(REWARD1)
		sm.consumeItem(FRAGMENT, 5)
	else:
		sm.systemMessage("Make sure you have enough space in your inventory..")
else:
	sm.systemMessage("One must have at least 5 fragments to unleash the magic powers..")