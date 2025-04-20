# 270000000
if not sm.hasQuestCompleted(3500): # time lane quest
    sm.chat("You have not completed the appropriate quest to enter here.")
else:
    sm.warp(270010000, 3)
sm.dispose()
