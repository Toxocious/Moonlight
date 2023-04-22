# Miracle Cube Fragment

FRAGMENT = 2430112
POT_SCROLL = 2049401
ADV_POT_SCROLL = 2049400

q = sm.getQuantityOfItem(FRAGMENT)

if q >= 10:
	if sm.canHold(ADV_POT_SCROLL):
		sm.giveItem(ADV_POT_SCROLL)
		sm.consumeItem(FRAGMENT, 10)
	else:
		sm.systemMessage("Make sure you have enough space in your inventory..")
elif q >= 5:
	if sm.canHold(POT_SCROLL):
		sm.giveItem(POT_SCROLL)
		sm.consumeItem(FRAGMENT, 5)
	else:
		sm.systemMessage("Make sure you have enough space in your inventory..")
else:
	sm.systemMessage("One must have at least 5 fragments to unleash the magic powers..")