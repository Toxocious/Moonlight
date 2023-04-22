# Demon's Doorway (9201130) | Caution Falling Down

quest = 28219   # Treasure, and Valefor the Demon  Quest
mapid = 677000008   # Valefor Strolling Path (Map before Boss Map)

if sm.hasQuest(quest):
    if sm.sendAskYesNo("Would you like to enter?"):
        sm.warp(mapid, 0)
else:
    sm.sendSayOkay("#b(A strange doorway)")
