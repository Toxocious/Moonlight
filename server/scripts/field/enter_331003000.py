# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331003000
# Subway :: Subway Car #1
BLUE_SHIRT_GUY = 1531064
# if has quest 22730
sm.spawnNpc(BLUE_SHIRT_GUY, 416, 47)
sm.showNpcSpecialActionByTemplateId(BLUE_SHIRT_GUY, "summon")
sm.playSound("Sound/Field.img/flowervioleta/wink")
sm.cameraSwitchNormal("go_next", 1000)
sm.unlockForIntro()