# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331003200
# Subway :: Subway Car #3
JAY = 1531001
GIRL = 1531067

sm.spawnNpc(GIRL, 699, 47)
sm.showNpcSpecialActionByTemplateId(GIRL, "summon")

sm.spawnMob(2700303, 250, 57, False)
sm.spawnMob(2700303, 250, 57, False)
sm.spawnMob(2700303, 250, 57, False)
sm.spawnMob(2700303, 250, 57, False)
sm.spawnMob(2700303, 250, 57, False)


sm.spawnMob(2700304, 300, 57, False)
sm.spawnMob(2700304, 300, 57, False)
sm.spawnMob(2700304, 300, 57, False)
sm.spawnMob(2700304, 300, 57, False)
sm.spawnMob(2700304, 300, 57, False)

sm.spawnMob(2700305, 350, 57, False)
sm.spawnMob(2700305, 350, 57, False)
sm.spawnMob(2700305, 350, 57, False)
sm.spawnMob(2700305, 350, 57, False)
sm.spawnMob(2700305, 350, 57, False)

sm.setSpineObjectEffectPlay(True, "subway_bg", "outside", True, False)
sm.setSpineObjectEffectPlay(True, "subway_main", "outside", True, False)

sm.unlockForIntro()
sm.playSound("Sound/Field.img/flowervioleta/wink")
sm.cameraSwitchNormal("go_next", 1000)
sm.addPopUpSay(JAY, 2000, "#face10#Watch out, there are more on the way!", "")