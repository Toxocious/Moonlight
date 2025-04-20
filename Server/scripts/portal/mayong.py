# Portal to Manon
MANONS_DARK_FOREST = 924000200

if sm.hasQuest(1451) or sm.hasQuest(1453) or sm.hasQuest(1455) or sm.hasQuest(1457) or sm.hasQuest(1459):
    sm.warpInstanceIn(MANONS_DARK_FOREST, False)
    sm.dispose()
sm.warp(240020401, 3)
