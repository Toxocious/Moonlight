# Special Training Master

medal = 1142246

if sm.canHold(medal) and sm.hasQuestCompleted(23060):
    sm.chatScript("You have earned a new medal.")
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
