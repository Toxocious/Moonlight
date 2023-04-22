lotusSecondPhase = 8950001
lotusThirdPhaseMapId = 350060600

sm.spawnLotus(1, 1)
sm.waitForMobDeath(lotusSecondPhase)
sm.warpInstanceIn(lotusThirdPhaseMapId, True)
