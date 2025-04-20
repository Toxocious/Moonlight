lotusFirstPhase = 8950000
lotusSecondPhaseMapId = 350060500

sm.spawnLotus(0, 1)
sm.waitForMobDeath(lotusFirstPhase)
sm.warpInstanceIn(lotusSecondPhaseMapId, True)
