# 270010100
if not sm.hasQuestCompleted(3501): # time lane quest
    sm.chat("You have not completed the appropriate quest to enter here.")
else:
    sm.warp(270010110, 0)
sm.dispose()
