# (Lv30) Official Knight

medal = 1142067

if sm.canHold(medal):
    sm.chatScript("You have earned a new medal.")
    sm.startQuest(parentID)
    sm.completeQuest(parentID)