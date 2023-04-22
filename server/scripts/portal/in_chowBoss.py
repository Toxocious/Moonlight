# Portal for entering Chao

CAVE_ENTRANCE = 2133005 # NPC ID
DEEP_INSIDE_THE_CAVE = 300010420 # MAP ID
BOULDER_MOUNTAIN_ENTRANCE = 300010400 # MAP ID 2
BOULDER_MOUNTAIN_MENACE = 31223 # QUEST ID
time = 7 * 60 # 7 MINUTES

sm.setSpeakerID(CAVE_ENTRANCE)

if sm.hasQuest(BOULDER_MOUNTAIN_MENACE) or sm.hasQuestCompleted(BOULDER_MOUNTAIN_MENACE):
    response = sm.sendAskYesNo("You sense something inside the dark cave. "
                               "Do you want to enter?")
    if response:
        sm.warpInstanceIn(DEEP_INSIDE_THE_CAVE)
        sm.setInstanceTime(time, BOULDER_MOUNTAIN_ENTRANCE, 2)
        sm.invokeAfterDelay(time * 1000, "chatRed", "Leaving the cave...")
else:
    sm.sendSayOkay("You sense nothing inside the cave.")