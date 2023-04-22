# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331003100
# Subway :: Subway Car #2
JAY = 1531001
BLONDE_LADY = 1531065
STUDENT = 1531066
sm.spawnNpc(BLONDE_LADY, 373, 47)
sm.showNpcSpecialActionByTemplateId(BLONDE_LADY, "summon")

sm.spawnNpc(STUDENT, 680, 47)
sm.showNpcSpecialActionByTemplateId(STUDENT, "summon")

sm.spawnMob(2700303, 250, 57, False)
sm.spawnMob(2700303, 260, 57, False)
sm.spawnMob(2700303, 270, 57, False)

sm.spawnMob(2700304, 280, 57, False)
sm.spawnMob(2700304, 290, 57, False)
sm.spawnMob(2700304, 300, 57, False)

sm.spawnMob(2700305, 310, 57, False)
sm.spawnMob(2700305, 320, 57, False)
sm.spawnMob(2700305, 330, 57, False)
sm.spawnMob(2700305, 340, 57, False)

sm.setSpineObjectEffectPlay(True, "subway_bg", "outing", False, False)
sm.setSpineObjectEffectPlay(True, "subway_main", "outing", False, False)

sm.setSpineObjectEffectAddPlay(True, "subway_bg", "outside", True)
sm.setSpineObjectEffectAddPlay(True, "subway_main", "outside", True)

sm.unlockForIntro()
sm.playSound("Sound/Field.img/flowervioleta/wink")
sm.cameraSwitchNormal("go_next", 1000)
sm.addPopUpSay(JAY, 2000, "#face10#Kinesis, monsters dead ahead!", "")