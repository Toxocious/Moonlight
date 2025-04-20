# Used to portal you to Root Abyss's Quest Map
if sm.hasQuest(30000) or sm.hasQuest(30003):
    sm.warp(910700200, 0) # Root Abyss Quest Map - (NOT the actual map)
else:
    sm.warp(105200000, 0) # Root Abyss
sm.dispose()
