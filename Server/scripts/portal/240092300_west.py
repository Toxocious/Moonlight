if sm.hasQuest(31348): # Colossal Clean Up 4
    sm.warpInstanceIn(924030000) # Hidden Street : Stone Colossus

elif sm.hasQuestCompleted(31348): # Colossal Clean Up 4
    sm.warp(240092400) # Stone Colossus Torso 3

else:
    sm.chat("This path is blocked by a mysterious presence.")