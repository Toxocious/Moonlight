# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001120
# Hideout  :: Training Room 2
KINESIS = 1531000
JAY = 1531001

if "1" not in sm.getQuestEx(22700, "E2"):
    sm.setNpcOverrideBoxChat(KINESIS)
    sm.sendNext("Jay, I feel so slow walking around like this. I'm going to switch to my speedier moves.")

    sm.setNpcOverrideBoxChat(JAY)
    sm.sendSay("#face9#Fine, whatever! Just ignore the test plan I spent hours on... Okay, I updated my database with your #bTriple Jump#k and #bAttack Skills#k for the final stage. Go nuts, dude.")

    sm.lockForIntro()
    sm.playSound("Sound/Field.img/masteryBook/EnchantSuccess")
    sm.showClearStageExpWindow(600)
    sm.giveExp(600)

    sm.playExclSoundWithDownBGM("Voice3.img/Kinesis/guide_04", 100)
    sm.sendDelay(2500)

    sm.unlockForIntro()
    sm.warp(331001130, 0)
    sm.setQuestEx(22700, "E1", "1")