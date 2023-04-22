# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001130
# Hideout  :: Training Room 3

if "1" not in sm.getQuestEx(22700, "E3"):
    sm.lockForIntro()
    sm.playSound("Sound/Field.img/masteryBook/EnchantSuccess")
    sm.showClearStageExpWindow(600)
    sm.giveExp(600)

    sm.playExclSoundWithDownBGM("Voice3.img/Kinesis/guide_08", 100)
    sm.sendDelay(2500)

    sm.setQuestEx(22700, "E3", "1")
    sm.unlockForIntro()
    sm.warp(331001000, 2)