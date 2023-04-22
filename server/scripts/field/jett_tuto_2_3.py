# Created by MechAviv
# Map ID :: 620100022
# Spaceship : Corridor

sm.hideUser(True)
OBJECT_1 = sm.sendNpcController(9201277, -270, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(9201278, -180, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(9201279, -90, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(9270083, 610, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
sm.moveNpcByObjectId(OBJECT_4, True, 100, 100)
sm.sendDelay(1200)


sm.moveCamera(False, 150, 200, -167)


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Jett was all sorts of awesome today. I've never seen anyone so strong.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("?")


sm.sendDelay(1200)


sm.setSpeakerID(9201278)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Yeah, that Core thing is just amazing. Jett's unstoppable... I bet even Captain Burke couldn't win that fight.")


sm.setSpeakerID(9201279)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Hah, that's kind of sad.")


sm.setSpeakerID(9201279)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I mean, maybe Jett should just be the captain. The king trusts Jett more anyway. We'd probably all be better off.")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("That's what everyone's been saying. Ah well, let's get to the party before all the food is gone.")


sm.sendDelay(1200)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext(".......")


sm.sendDelay(1200)


sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.sendNpcController(OBJECT_4, False)
sm.warp(620100030, 0)
