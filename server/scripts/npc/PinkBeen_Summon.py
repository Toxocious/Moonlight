# is spawned by the pink bean warp-in npc

chaos = 0
CHAOS_MAP = 270051100
if sm.getFieldID() == CHAOS_MAP:
	chaos = 100
    
FINAL_STATUE = 8820002 + chaos
IMMORTAL_PINKBEAN = 8820000 + chaos
INITIAL_MOB = 8820009 + chaos
KRITAS = 2141000

PX = 9
PY = -42

sm.sendSayOkay("You have disturbed great forces.. You will live to regret this!")
sm.removeNpc(KRITAS) # TODO add the wz effect that accompanies pink bean dying
sm.spawnMob(INITIAL_MOB, PX, PY, False)

sm.spawnMob(IMMORTAL_PINKBEAN, PX, PY, False) # this is the pink bean that taunts you while while you kill the statues

mob = sm.waitForMobDeath(FINAL_STATUE)
sm.killMob(IMMORTAL_PINKBEAN, True)