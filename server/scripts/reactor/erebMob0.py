# Spawns mobs that drop Proof of Exam for Cygnus tutorial.

TRAINING_TIMU = 9300732

reactor.incHitCount()

if reactor.getHitCount() >= 3:
	for x in range(2):
		sm.spawnMobOnChar(TRAINING_TIMU)

	sm.removeReactor()