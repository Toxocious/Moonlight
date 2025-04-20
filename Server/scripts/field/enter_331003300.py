# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331003300
# Subway :: Subway Car #4
JAY = 1531001

sm.spawnMob(2700306, 400, 57, False)
sm.spawnMob(2700307, 425, 57, False)
sm.spawnMob(2700308, 450, 57, False)

sm.unlockForIntro()
sm.createQuestWithQRValue(22733, "")
sm.setQuestEx(22700, "kinePro", "0")
sm.playSound("Sound/Field.img/flowervioleta/wink")
sm.cameraSwitchNormal("pt_DP", 1000)
sm.addPopUpSay(JAY, 2000, "#face10#K, those are the stronger ones! Take them out first!", "")

while sm.hasMobsInField():
    sm.waitForMobDeath()

if sm.getQuestEx(22700, "kinePro").equals("0"):
    sm.setQRValue(22733, "001001001")
    sm.showFieldEffect("monsterPark/clearF", 0)