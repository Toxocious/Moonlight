# 270010400
if not sm.hasQuestCompleted(3504): # time lane quest
    sm.chat("You have not completed the appropriate quest to enter here.")
else:
    sm.warp(270010500, 0)
sm.dispose()
