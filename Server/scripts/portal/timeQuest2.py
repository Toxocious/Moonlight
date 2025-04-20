# 270010200
if not sm.hasQuestCompleted(3502): # time lane quest
    sm.chat("You have not completed the appropriate quest to enter here.")
else:
    sm.warp(270010300, 0)
sm.dispose()
