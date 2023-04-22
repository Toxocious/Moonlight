# Created by MechAviv
# Map ID :: 620100023
# Spaceship : Captain's Quarters
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)

OBJECT_1 = sm.sendNpcController(9270083, 50, -75)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(9201286, -450, -143)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.showFieldEffect("newPirate/text6", 0)
sm.sendDelay(1900)


sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Burke... what WAS that? You put yourself and the crew in danger. That's not the Burke I know...")


sm.sendDelay(1200)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("I just... I just wanted to be as good as you.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Look, I'm not even that good! All my power comes from the Core. It's not me.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I never wanted to be a bounty hunter anyway. You're the-")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Stop, Jett! That's the thing!")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("?")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You never wanted to be a bounty hunter! I did. But YOU'RE the one with the Core! It's not...")


sm.sendDelay(1900)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("...I think you just need some rest. I'll take care of the crew. You just relax... and think things over.")


sm.forcedInput(2)
sm.sendDelay(3000)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Aargh...!")


sm.sendDelay(3000)


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Kshhhhhh... Kshhhh...")


sm.sendDelay(1200)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("?")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("What the...? Is this thing busted?")


sm.moveNpcByObjectId(OBJECT_1, True, 300, 100)
sm.sendDelay(4000)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Who are you?")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("This... Kshhh... Maple World... Kshhhhh... We are... Kshh.... revive the master...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Maple World... I've heard of that place. What do you want with me?")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("We need... Kshhhhh.... power of the Core...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("The Core? Then you're out of luck. It's not mine.")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Kshh... I know... Kshhhh... Bring... to us... Kshhhhhh...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Yeah, sure. Why would I betray my own friend for some creepy stranger's request?")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Friend....? Kshhhh... An ungrateful... Kshhh... stealing your position... Kshh... Truly your friend...?")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You've been bugging our ship!")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Ha ha ha... not too... difficult to find your ship... Kshhhh... the Core... more powerful than you'll ever know...")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Kshhhh... Bring the Core to Maple World... Kshhh... And Jett will lose... Kshhhhhh... you'll be at the top again...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("The top... again?")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You've been robbed of so many... Kshhhh... because of the Core....")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("...Let's assume I do this thing. I can't just drag Jett to the other side of the universe.")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Kshhhhhhh... Eliminate... the king... Kshhhhh... Blame Jett...")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("They will chase... Kshhhh... ends of the universe... Kshhhh... cannot return to your world...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("The king... I've never forgiven him for ignoring my struggle... And fawning over Jett...")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Kshhhh... Help us... and the power... is yours...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("...Can you guarantee Jett's safety? I'll bring you the Core, and that's all.")


sm.setSpeakerID(9201286)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Kshhhh... Exactly what... we want...")


sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.warp(620100024, 0)
