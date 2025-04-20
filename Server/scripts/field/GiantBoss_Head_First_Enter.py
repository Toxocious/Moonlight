from net.swordie.ms.enums import GolluxDifficultyType

golluxCoinID = 4310097
firstPhaseHeadID = 9390600
secondPhaseHeadID = 9390601
thirdPhaseHeadID = 9390602
golluxMobID = 9390623
secondGolluxMobID = 9390622
sm.blockGolluxAttacks()
if sm.golluxMapAlreadyVisited() is not True:
    sm.spawnGollux(0)
    sm.addCurrentGolluxMap()
    sm.spawnMobRespawnable(golluxMobID, -850, 0, True, 1, 10)
    sm.spawnMobRespawnable(secondGolluxMobID, 850, 0, True, 1, 10)
elif sm.hasMobById(secondPhaseHeadID):
    sm.changeFootHold("phase2-1", True)
    sm.changeFootHold("phase2-2", True)
elif sm.hasMobById(thirdPhaseHeadID):
    sm.changeFootHold("phase2-1", True)
    sm.changeFootHold("phase2-2", True)
    sm.changeFootHold("phase3", True)
if sm.hasMobById(firstPhaseHeadID):
    sm.waitForMobDeath(firstPhaseHeadID)
    sm.changeFootHold("phase2-1", True)
    sm.changeFootHold("phase2-2", True)
    sm.spawnGollux(1)
if sm.hasMobById(secondPhaseHeadID):
    sm.waitForMobDeath(secondPhaseHeadID)
    sm.changeFootHold("phase3", True)
    sm.spawnGollux(2)
    sm.createTimerGauge(100)
    sm.invokeAfterDelay(100000, "warpInstanceOut", 863010700)
if sm.hasMobById(thirdPhaseHeadID):
    sm.waitForMobDeath(thirdPhaseHeadID)
    coinsAmount = 10 * (3 - GolluxDifficultyType.getVal(sm.getGolluxDifficulty()) + 2)
    sm.giveItem(golluxCoinID, coinsAmount)
    sm.stopEvents()
    sm.warpInstanceOut(863010000)
    sm.clearGolluxClearedMaps()
