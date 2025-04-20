if sm.checkParty():
    if sm.hasQuest(31351): # [Stone Colossus] Colossal Clean Up 7
        sm.warpInstanceIn(240093310, True)
    elif sm.hasQuestCompleted(31351):
        sm.warpInstanceIn(240093300, True)
