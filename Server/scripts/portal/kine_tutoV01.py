# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001110
# Hideout  :: Training Room 1

if "1" not in sm.getQuestEx(22700, "V01"):
    sm.progressMessageFont(3, 20, 20, 0, "Move by pressing the move or jump keys.")
    sm.setQuestEx(22700, "V01", "1")
