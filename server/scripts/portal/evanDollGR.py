# Created by MechAviv
# ID :: [100040000]
# Golem's Temple : Golem's Temple Entrance
if sm.hasQuest(25584) or sm.hasQuest(25583):
    sm.warp(910600201, 0)
else:
    sm.systemMessage("There's a suspicious-looking puppet in this building. It seems to be locked. You can't get in.")