# Special Training Intermediate

medal = 1142243

if sm.canHold(medal):
    sm.chatScript("You have earned a new medal.")
    sm.giveItem(medal)
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
