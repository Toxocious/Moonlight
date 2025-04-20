# Demon's Doorway (9201131) | Blue Mushroom Forest 2

quest = 28238   # Treasure, and Amdusias the Demon  Quest
mapid = 677000002   # Amdusias Strolling Path (Map before Boss Map)

if sm.hasQuest(quest):
    if sm.sendAskYesNo("Would you like to enter?"):
        sm.warp(mapid, 0)
else:
    sm.sendSayOkay("#b(A strange doorway)")
