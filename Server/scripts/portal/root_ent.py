# Portal to Root Abyss
if sm.hasQuest(30000) or sm.hasQuestCompleted(30000): # Root Abyss quest -  [Root Abyss] An Urgent Summons
    sm.warp(105010200, 0) # Secret Swamp
else:
    sm.chat("The path is blocked by a thick fog.")
sm.dispose()
