# Created by MechAviv
# Map ID :: 620100025
# Spaceship : Corridor
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)

sm.completeQuest(5757)
sm.showFieldEffect("newPirate/text6", 0)
sm.sendDelay(1900)


OBJECT_1 = sm.sendNpcController(9201277, 80, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.showEffect("Skill/000.img/skill/0001227/effect", 0, 10, -20, 0, OBJECT_1, False, 0)
sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Chief! Code red!")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("What is it?")


sm.forcedInput(2)
sm.sendDelay(400)


sm.forcedInput(0)
sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("The king...he's... He's been assassinated! But the thing is...")


sm.sendDelay(1200)


OBJECT_2 = sm.sendNpcController(9270083, -520, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Jett!")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Burke! Where have you been?\r\nDid you hear? The king was assassinated!")


sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.moveNpcByObjectId(OBJECT_2, False, 300, 100)
sm.sendDelay(4000)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("...I've heard. Look at this.")


sm.showFieldEffect("newPirate/pendant_w", 0)
sm.sendDelay(3000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg1/1", 2000, 0, -80, -2, -2, False, 0)
sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Wha......What?! How am I wanted...? For what...? Is this real, Burke?!")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("We were about to tell you.")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Everyone says you were the one who assassinated the king.")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("What?! No! It's not me! I've been right here on the ship!")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("We know, but-")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("There's no time! The royal guards must already be homing in on us. You need to go!")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("No, I won't run! That'll only convince them that I did it! I'm innocent, and I'll prove it!")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I have no reason to assassinate the king!")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Jett, there are witnesses. I don't know how, but... No one will believe you. And the people are angry.")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I can't believe it... I didn't do anything...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Jett, I know you're innocent. We all do. But you have to get out of here. Get somewhere safe first, then think about what we can do to get you out of this mess.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("It's only been a few hours, but everyone's already convinced you're guilty. I mean, just look at this reward.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You're a bounty hunter. You know how this works.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Is running away... really the only option I have?")


OBJECT_3 = sm.sendNpcController(9201278, 200, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.showEffect("Skill/000.img/skill/0001227/effect", 0, 10, -20, 0, OBJECT_3, False, 0)
sm.setSpeakerID(9201278)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Chief! The royal guardsmen are here! If you're going, it's got to be now. Don't worry, we'll buy you some time!")


sm.setSpeakerID(9201278)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("No, I don't want anyone else involved in this mess! I don't know how I got into it, but I'm going to get myself out of it.")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You've saved our lives so many times. Let us do this one thing for you.")


sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("...You guys...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You don't have much time! We've got your back, now go!")


sm.setSpeakerID(9201278)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Please... Take care!")


sm.setSpeakerID(9201278)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("...We'll meet again, I promise!")


sm.moveNpcByObjectId(OBJECT_2, True, 400, 100)
sm.sendNpcController(OBJECT_2, False)
sm.forcedInput(1)
sm.sendDelay(4000)


sm.forcedInput(0)
sm.hideUser(True)
sm.setSpeakerID(9201277)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("We've got to do our part! Even if it means putting our lives on the line...")


sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_3, False)
sm.warp(620100026, 0)
