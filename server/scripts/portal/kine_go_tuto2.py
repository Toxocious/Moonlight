# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001110
# Hideout  :: Training Room 1

if "1" not in sm.getQuestEx(22700, "E1"):
    sm.lockForIntro()
    sm.playSound("Sound/Field.img/masteryBook/EnchantSuccess")
    sm.showClearStageExpWindow(350)
    sm.giveExp(350)

    sm.playExclSoundWithDownBGM("Voice3.img/Kinesis/guide_04", 100)
    sm.sendDelay(2500)

    sm.setQuestEx(22700, "E1", "1")
    sm.unlockForIntro()
    sm.warp(331001120, 0)