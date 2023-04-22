# Kaiser's Successor

medal = 1142487

if sm.canHold(medal):
    sm.chatScript("You have earned a new medal.")
    sm.startQuest(parentID)
    sm.completeQuest(parentID)