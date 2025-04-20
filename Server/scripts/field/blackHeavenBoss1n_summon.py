lotusFirstPhase = 8950000
lotusSecondPhaseMapId = 350060800

sm.spawnLotus(0, 0)
sm.waitForMobDeath(lotusFirstPhase)
sm.warpInstanceIn(lotusSecondPhaseMapId, True)
