# Created by MechAviv
# Map ID :: 620100042
# Nautilus : Wrecked Airship 2

OBJECT_1 = sm.sendNpcController(9270084, 143, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendSessionValue("mastema", OBJECT_1)
sm.forcedInput(2)
sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg1/16", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(1900)


sm.forcedInput(0)
sm.sendDelay(2000)


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("#b#p9270084##k! What's with the outfit?")


sm.forcedInput(2)
sm.sendDelay(1000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg1/17", 2000, 0, -100, -2, -2, False, 0)
sm.forcedInput(0)
sm.sendDelay(2000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg2/9", 2000, 0, -80, -2, -2, False, 0)
sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg2/9", 0, 0, -100, 0, OBJECT_1, False, 0)
sm.sendDelay(2000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg1/18", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(2000)


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("S-seriously, #p9270084#, what's with the creepy get-up? Are you-- THE CORE!")


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I knew you were the best captain ever! Toss me the core and I'll get us off of this backwater planet!")


sm.sendDelay(2000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg2/11", 0, 0, -100, 0, OBJECT_1, False, 0)
sm.sendDelay(2000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg2/3", 0, 0, -100, 0, OBJECT_1, False, 0)
sm.sendDelay(2000)


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Do you feel powerless without it? Do you feel like you can't really make a difference in the world?")


sm.showNpcSpecialActionByObjectId(OBJECT_1, "alert", 0)
sm.sendDelay(2000)


sm.playSound("demonSlayer/arkAttack0", 100)
sm.showEffect("Effect/DirectionNewPirate.img/effect/tuto/pirateAttack", 2000, 0, -50, -2, -2, False, 0)
sm.forcedInput(1)
sm.sendDelay(500)


sm.forcedInput(0)
sm.sendDelay(100)


sm.forcedInput(2)
sm.sendDelay(100)


sm.localEmotion(5, 2000, False)
sm.forcedInput(4)
sm.sendDelay(2000)


sm.localEmotion(5, 3500, False)
sm.sendDelay(1500)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg2/21", 2000, 20, -70, -2, -2, False, 0)
sm.sendDelay(1000)


sm.sendDelay(2000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg1/19", 2000, 20, -70, -2, -2, False, 0)
sm.sendDelay(2000)


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Wh-what do you think you're doing?! Stop jokin' around and give me that core before I start rethinking our friendship! ")


sm.sendDelay(1000)


sm.sendDelay(2000)


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("You really thought of me as a friend? All my life, I walked in your shadow. No matter how much I trained, I couldn't surpass the power of your core.")


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Letting you live the life I've had to lead is my last gift to you, friend.")


sm.sendDelay(1000)


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Nobody outside my family can use the core. You know that! It ain't gonna be anything but a useless rock to you!")


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Then it'll be a useless rock to the both of us and we'll finally be equals... the way it was meant to be. Now, farewell...")


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Wait! Just, just tell me you didn't assassinate the king. Tell me you didn't do it, #p9270084#...")


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("We've all got secrets, and that's one I'm not ready to give up just yet. ")


sm.sendDelay(2000)
sm.reservedEffect("Effect/DirectionNewPirate.img/newPirate/face05")


sm.setSpeakerID(9270084)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("#b#p9270084##k... it... it didn't have to be like this... ugh...")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.warp(620100043, 0)
