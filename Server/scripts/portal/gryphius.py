# Portal to Griffin
DARK_GRIFFEY_FOREST = 924000201

if sm.hasQuest(1451) or sm.hasQuest(1453) or sm.hasQuest(1455) or sm.hasQuest(1457) or sm.hasQuest(1459):
    sm.warpInstanceIn(DARK_GRIFFEY_FOREST, False)
    sm.dispose()
sm.warp(240020101, 3)
