# Farm Center -> Large Forest Trail
if sm.hasQuestCompleted(22010) or sm.hasQuest(22010):
    sm.warp(100030310, 0)
    sm.dispose()
else:
    sm.chat("You are not allowed to leave the farm yet.")
    sm.dispose()