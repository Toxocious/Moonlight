# 910700200 Root Abyss : Colossal Root  (Quest Map)
if sm.hasQuest(30002):
    sm.setPlayerAsSpeaker()
    sm.sendNext("I should tell that girl about the exit first.")
    sm.completeQuest(30002)
else:
    sm.warp(105010200, 0) # Secret Swamp
sm.dispose()
