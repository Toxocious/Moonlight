# 310050100
if sm.hasQuest(23033) or sm.hasQuest(23034) or sm.hasQuest(23035) or sm.hasQuest(23164):
    sm.warp(931000200, 1)
    sm.chat("Destroy the Energy Conducting Device!")
    if not sm.hasMobsInField(931000200):
        sm.invokeAfterDelay(500, "spawnMob", 9001032, 680, 18, False)
else:
    sm.chat("This seems to be a forbidden area. Better stay away.")