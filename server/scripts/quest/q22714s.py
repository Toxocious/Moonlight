# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001130
# Hideout  :: Training Room 3
KINESIS = 1531000
JAY = 1531001

sm.startQuest(parentID)
sm.lockForIntro()
sm.removeAdditionalEffect()

sm.setIntroBoxChat(KINESIS)
sm.sendNext("Is that all?")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face9#No, of course not. Don't get ahead of yourself, K.")
sm.sendSay("#face9#I'm tracking your Psychic Points #g(PP)#k so you'll know when you can use your ultimate. That's what I call that super technique you came up with. Seemed appropriate.")
sm.sendSay("#face9#You'll get Psychic Points just by using psychokinesis abilities. I have to say, you have a very convenient power set, K.")

sm.progressMessageFont(3, 20, 20, 0, "Collect Psychic Points to unleash your ultimate skill.")
sm.avatarOriented("UI/tutorial.img/kinesis/2")
sm.playExclSoundWithDownBGM("Voice3.img/Kinesis/guide_14", 100)
sm.sendDelay(2500)

sm.sendNext("#face9#Your ultimate skill should be on your #g[END]#k key, if you haven't moved it. Go ahead and let loose with it... I reinforced the walls after last time.")

sm.playSound("Sound/SoundEff.img/blackHeaven/metaldrop")
sm.spawnMob(2700300, 200, -238, False)
sm.spawnMob(2700300, 250, -238, False)
sm.spawnMob(2700300, 300, -238, False)
sm.spawnMob(2700300, 350, -238, False)
sm.spawnMob(2700300, 400, -238, False)
sm.spawnMob(2700300, 450, -238, False)
sm.spawnMob(2700300, 500, -238, False)
sm.setFuncKeyByScript(True, 142001002, 79)
sm.cameraSwitchNormal("pt_VP", 1000)
sm.sendDelay(1000)

sm.sendNext("#face9#Ultimate: Metal Press#g [END]#k will crush everything in its path, including targets high above you.")
sm.unlockForIntro()

while sm.hasMobsInField():
    sm.waitForMobDeath()
if sm.getQuestEx(22700, "kinetuto2").equals("0"):
    sm.setQuestEx(22700, "kinetuto2", "1")

sm.playSound("Party1/Clear")
sm.showFieldEffect("monsterPark/clear", 0)