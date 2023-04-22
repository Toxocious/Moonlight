if sm.hasQuest(23210):
    sm.warpInstanceIn(931050100, False)
elif sm.hasQuest(23213):
    sm.addQRValue(23206, "1")
    sm.warp(931050110, 1)
elif sm.hasQuestCompleted(23213):
    sm.warp(931050110, 1)