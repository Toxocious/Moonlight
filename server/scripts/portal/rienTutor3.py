# 140090300
if sm.hasQuestCompleted(21012):
    sm.warp(140090500, 1)
    sm.completeQuest(21013)
    sm.addLevel(1)
else:
    sm.systemMessage("You must complete the quest before proceeding to the next map.")