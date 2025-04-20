# Acorn tree reactor | edelstein
REQUEST_FROM_A_DOCTOR = 23003
WHOLE_ACORN = 4034738

reactor.incHitCount()

if reactor.getHitCount() >= 3:
	if sm.hasQuest(REQUEST_FROM_A_DOCTOR) and not sm.hasItem(WHOLE_ACORN, 2):
	    sm.dropItem(WHOLE_ACORN, sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY())

	sm.removeReactor()
