# Created by MechAviv
# ID :: [4000021]
# Maple Road : Entrance to Adventurer Training Center

sm.setSpeakerID(12100)
selection = sm.sendNext("This is the perfect place to train your basic skills. Where do you want to train?\r\n#b#L0#Adventurer Training Center 1#l\r\n#b#L1#Adventurer Training Center 2#l\r\n#b#L2#Adventurer Training Center 3#l\r\n#b#L3#Adventurer Training Center 4#l")
if selection == 0:
    sm.warp(4000022, 4)
elif selection == 1:
    sm.warp(4000023, 4)
elif selection == 2:
    sm.warp(4000024, 4)
elif selection == 3:
    sm.warp(4000025, 4)