# Created by MechAviv
# Map ID :: 620100024
# Spaceship : Jett's Room

sm.hideUser(True)
sm.completeQuest(5757)
OBJECT_1 = sm.sendNpcController(9270083, -230, -85)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.moveNpcByObjectId(OBJECT_1, False, 100, 100)
sm.sendDelay(1900)


sm.completeQuest(5757)
sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Jett... I'm so sorry, but I need your power.\r\nJust the Core... Please forgive me.")


sm.sendDelay(3000)


sm.sendNpcController(OBJECT_1, False)
sm.warp(620100025, 0)
