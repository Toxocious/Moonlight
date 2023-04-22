# Forgotten Hero

medal = 1142672

if sm.canHold(medal):
    sm.chatScript("You have earned a new medal.")
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.giveSkill(20051005, 1, 1)