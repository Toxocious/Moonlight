# Created by MechAviv
# [Magic Library Checker]  |  [1032218]
# Ellinia : Magic Library
if "1" not in sm.getQuestEx(25566, "c1"):
    sm.setQuestEx(25566, "c1", "1")
    sm.chatScript("You search the Magic Library.")