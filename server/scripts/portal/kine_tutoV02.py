# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001110
# Hideout  :: Training Room 1
KINESIS = 1531000
JAY = 1531001
if "1" not in sm.getQuestEx(22700, "V02"):
    sm.addPopUpSay(KINESIS, 2000, "Jay, this is too easy!", "")
    sm.addPopUpSay(JAY, 2000, "#face9#I'm still calibrating, you smuglord! I'll prep some monsters for the next room.", "")
    sm.setQuestEx(22700, "V02", "1")