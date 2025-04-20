# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001130
# Hideout  :: Training Room 3
KINESIS = 1531000
JAY = 1531001

sm.lockForIntro()
sm.removeAdditionalEffect()
sm.forcedFlip(True)

sm.setIntroBoxChat(JAY)
sm.sendNext("#face9#You're clear to go sickhouse, K. Open up with your attack skills.")
sm.sendSay("#face9#I'll switch your psy-limiter to #bTier 1#k.")
sm.setJob(14200)
sm.addSP(1, True)
# here goes reset stats
sm.giveAndEquip(1353200)
sm.giveSkill(142001000, 1, 10)
sm.giveSkill(142001001, 1, 20)
sm.giveSkill(142001004, 1, 5)
sm.giveSkill(142001002, 1, 10)
sm.setFuncKeyByScript(True, 142001000, 42)
sm.setFuncKeyByScript(True, 142001001, 83)
sm.unlockForIntro()

sm.sendNext("#face9#Psychic Force #g[SHIFT]#k will push your targets in one direction.")
sm.sendSay("#face9#Kinetic Crash #g[DEL]#k will lift enemies with your psychokinesis and slam them into the floor.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("#gSHIFT#k to push and #gDEL#k to attack. Simple")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face9#So you say. I'd like to get some data on your triple jump, too, so mash your Jump key to zip all over the place for me.")
sm.playSound("Sound/SoundEff.img/blackHeaven/metaldrop")
sm.spawnMob(2700300, -50, 63, False)
sm.spawnMob(2700300, 50, 63, False)
sm.spawnMob(2700300, 150, 63, False)
sm.spawnMob(2700300, 250, 63, False)
sm.spawnMob(2700300, 350, 63, False)
sm.spawnMob(2700300, 450, 63, False)
sm.spawnMob(2700300, 550, 63, False)
sm.playSound("Sound/Field.img/flowervioleta/wink")
sm.cameraSwitchNormal("go_ajit", 1000)
sm.setQuestEx(22700, "kinetuto2", "0")
