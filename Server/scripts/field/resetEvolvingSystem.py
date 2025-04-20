# Hidden street : Evolution Lab
#if not sm.hasQuestCompleted(1802):
    #TODO ugly workaround
#    sm.deleteQuest(1802)
#    sm.createQuestWithQRValue(1802, "done")
#if sm.hasQuest(1846):
#    sm.warpInstanceIn(957020002)
if not sm.hasQuest(1845) or not sm.hasQuest(1847):
    sm.startQuest(1845)
    sm.startQuest(1847)