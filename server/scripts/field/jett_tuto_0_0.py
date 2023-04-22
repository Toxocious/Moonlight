# Created by MechAviv
# Map ID :: 620100000
# Planet Cerberus : Somewhere on the planet Cerberus

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.giveAndEquip(1003530)
sm.hideUser(True)
OBJECT_1 = sm.sendNpcController(9201274, 50, -150)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(9201275, -50, -150)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.showFieldEffect("newPirate/text3", 0)
sm.sendDelay(1900)


sm.setSpeakerID(9201275)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("All right, I found you! Taste my space beam, you vicious criminal!")


sm.showNpcSpecialActionByObjectId(OBJECT_2, "attack", 0)
sm.setSpeakerID(9201274)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Oh yeah? Well, this shield from the royal guards will defend me against anything and everything!")


sm.showNpcSpecialActionByObjectId(OBJECT_1, "attack", 0)
sm.sendDelay(2000)


sm.setSpeakerID(9201275)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("...Y'know, it's kinda weird that a vicious criminal is using a good guy shield.")


sm.setSpeakerID(9201274)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I don't care. I don't wanna be a nasty criminal or a space pirate anyway!\r\nA royal guard is much cooler. I'm gonna live in the castle and protect the king!")


sm.setSpeakerID(9201275)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("But... why? Your family is like the most famous bounty hunter crew ever.\r\nI bet your parents want to take you on all kinds of adventures.")


sm.setSpeakerID(9201274)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("It's cool, but it gets old after awhile. Sometimes you want to find something you really, really like and just... hold on to it. Protect it.")


sm.setSpeakerID(9201274)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'm tired of being at home while my family vanishes off to parts unknown.")


sm.setSpeakerID(9201275)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("...I think your family is the coolest.\r\nYou guys chase bad dudes all over space. That's crazy.")


sm.setSpeakerID(9201275)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I...I wanna make a cool space crew, just like your family.\r\nThough I... don't have a family to adventure with... or anything as neat as the Core...")


sm.setSpeakerID(9201274)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Someday my dad's Core will be mine... And I'll get really strong!\r\nWhen I do, I'll get rid of all those jerks that hurt your family, Burke!")


sm.setSpeakerID(9201275)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("...Thanks, Jett.")


sm.setSpeakerID(9201274)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Uh-oh, it's already dinner time! Hurry up, Burke!")


sm.moveNpcByObjectId(OBJECT_1, True, 600, 100)
sm.sendDelay(2000)


sm.setSpeakerID(9201275)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("......")


sm.moveNpcByObjectId(OBJECT_2, True, 600, 100)
sm.sendDelay(2000)


sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.warp(620100010, 0)
