# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001120
# Hideout  :: Training Room 2

JAY = 1531001

sm.spawnMob(2700302, 705, 63, False)
sm.lockForIntro()
sm.forcedFlip(True)
sm.removeAdditionalEffect()

sm.setIntroBoxChat(JAY)
sm.sendNext("#face9#Okay, before we get to the real combat, let me capture a baseline of your ablities. Destroy Training Robot A there so I can measure your basic power.")

sm.setQuestEx(22700, "kinetuto", "0")
sm.progressMessageFont(3, 20, 20, 0, "Defeat the target using basic attacks and skills.")
sm.unlockForIntro()
sm.avatarOriented("UI/tutorial.img/kinesis/0")
sm.playExclSoundWithDownBGM("Voice3.img/Kinesis/guide_06", 100)

sm.waitForMobDeath(2700302)
if sm.getQuestEx(22700, "kinetuto").equals("0"):
    sm.setQuestEx(22700, "kinetuto", "1")
    sm.playSound("Party1/Clear")