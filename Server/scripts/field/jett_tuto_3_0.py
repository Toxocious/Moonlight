# Created by MechAviv
# Map ID :: 620100030
# Dark Cave : Dark Cave

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)

sm.hideUser(True)
OBJECT_1 = sm.sendNpcController(9201277, -180, 37)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(9201278, -100, 37)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(9201279, -30, 37)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(9270083, 60, 37)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
sm.showFieldEffect("newPirate/text5", 0)
sm.sendDelay(1900)


sm.moveNpcByObjectId(OBJECT_4, False, 50, 100)
sm.sendDelay(1200)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("They're here... They've got nowhere else to run. I'm going to get them today!")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Captain, shouldn't we call for backup? This might-")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("No! They're not getting away from me! I won't let them!")


sm.setSpeakerID(9201278)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("But what if it's a trap like last time? We were lucky Jett was there to-")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Jett? Jett?! What's so great about Jett? Jett is nothing without the Core! NOTHING!")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'M the captain here!")


sm.sendDelay(1200)


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Captain, calm down. We just need to be careful...")


sm.sendDelay(1200)


sm.tremble(0, 1300, 30)
sm.sendDelay(1200)


sm.setSpeakerID(9201278)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("What was that?")


OBJECT_5 = sm.sendNpcController(9201280, 440, 37)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
OBJECT_6 = sm.sendNpcController(9201281, 530, 37)
sm.showNpcSpecialActionByObjectId(OBJECT_6, "summon", 0)
OBJECT_7 = sm.sendNpcController(9201282, 590, 37)
sm.showNpcSpecialActionByObjectId(OBJECT_7, "summon", 0)
OBJECT_8 = sm.sendNpcController(9201283, 650, 37)
sm.showNpcSpecialActionByObjectId(OBJECT_8, "summon", 0)
OBJECT_9 = sm.sendNpcController(9201284, 710, 37)
sm.showNpcSpecialActionByObjectId(OBJECT_9, "summon", 0)
OBJECT_10 = sm.sendNpcController(9201285, 770, 37)
sm.showNpcSpecialActionByObjectId(OBJECT_10, "summon", 0)
sm.sendDelay(1900)


sm.setSpeakerID(9201280)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Ha! Gotcha! Burke, you fool. I heard you've been losing your edge. Guess the rumors were true.")


sm.setSpeakerID(9201280)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("And without Jett, you're easy pickings. Say goodnight, buddy.")


sm.moveNpcByObjectId(OBJECT_5, True, 20, 100)
sm.moveNpcByObjectId(OBJECT_6, True, 20, 100)
sm.moveNpcByObjectId(OBJECT_7, True, 20, 100)
sm.moveNpcByObjectId(OBJECT_8, True, 20, 100)
sm.moveNpcByObjectId(OBJECT_9, True, 20, 100)
sm.moveNpcByObjectId(OBJECT_10, True, 20, 100)
sm.sendDelay(1200)


sm.setSpeakerID(9201280)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Stop!")


sm.showEffect("Skill/000.img/skill/0001227/effect", 0, 0, 0, -2, -2, False, 0)
sm.hideUser(False)
sm.sendDelay(1200)


sm.setSpeakerID(9201280)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("...It's you!")


sm.setSpeakerID(9201280)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Glad I decided to drop in.")


sm.setSpeakerID(9201278)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Chief! Thank goodness!")


sm.setSpeakerID(9201278)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("You guys sit tight, I've got this!")


sm.setSpeakerID(9201280)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Rrgh, everyone, run for it! We can't take the one with the Core.")


sm.forcedAction(5, 0)
sm.showEffect("Skill/572.img/skill/5721064/effect", 0, 0, 0, -2, -2, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(9201280)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("N-no...")


sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.sendNpcController(OBJECT_4, False)
sm.sendNpcController(OBJECT_5, False)
sm.sendNpcController(OBJECT_6, False)
sm.sendNpcController(OBJECT_7, False)
sm.sendNpcController(OBJECT_8, False)
sm.sendNpcController(OBJECT_9, False)
sm.sendNpcController(OBJECT_10, False)
sm.warp(620100023, 0)
