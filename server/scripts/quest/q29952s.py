# A Hero, No More (Mercedes)

medal = 1142336

if sm.canHold(medal):
    sm.chatScript("You have earned a new medal.")
    sm.startQuest(parentID)
    sm.completeQuest(parentID)