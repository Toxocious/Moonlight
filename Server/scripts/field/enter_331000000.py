# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331000000
# Main Street :: City Center

# if (22700, "coNight", "1"):
if "1" in sm.getQuestEx(22700, "coNight"):
    sm.setSpineObjectEffectAlpha(False, "sinkHole", 0, 0)
    sm.changeBGM("Bgm43/Unknown Part Of City", 0, 0)
    sm.setObjectEffectPlay("city", "night", True, False)
    sm.setSpineObjectEffectPlay(False, "car", "day2night", False, False)
    sm.setSpineObjectEffectAddPlay(False, "car", "night", True)
    sm.setSpineObjectEffectStop(False, "car", False)
    sm.setSpineObjectEffectPlay(False, "eastSub", "night", True, False)