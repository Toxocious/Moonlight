# Created by MechAviv
# Map ID :: 620100010
# Planet Cerberus : In Front of the Castle
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.setStandAloneMode(True)
sm.showFieldEffect("newPirate/text4", 0)
sm.sendDelay(1900)


sm.forcedInput(1)
sm.sendDelay(3000)


sm.forcedInput(0)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Ugh, failed again... Why can't I pass this thing? Why is it so hard to become a royal guard?")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Team operations?! Fights are supposed to be mano-a-mano. Everyone else is just in the way!")


sm.sendDelay(1200)


OBJECT_1 = sm.sendNpcController(9270083, -630, -131)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.moveNpcByObjectId(OBJECT_1, False, 400, 100)
sm.sendDelay(5500)


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("How'd it go, Jett?")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Five failures and counting. I just don't get it. I have the power of the Core... What could be the problem?")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("My dad wanted me to follow in his footsteps as a bounty hunter...\r\nMaybe the universe is trying to tell me something with all these failures...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Don't think of it as failure. Think of it as... opportunity!")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Why don't you join my bounty hunters? I've always thought you'd be perfect.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("...Do you really think that's what I should do?")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Actually, yes. Think of your family, Jett.\r\nThey only wanted one thing, and that's for you to become a great bounty hunter, battle evil, and carry on their legacy.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Protecting the king is important, of course. But maybe there's a better way for you to do that.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("...You know what? You're right.\r\nOkay, I'll roll with you and your hunters for awhile, Burke. I owe you.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Owe me? Hardly. Your family took me in after I lost my own family to pirates.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("It's your family that fueled my dreams to become a bounty hunter, and look at me now!\r\nI've always thought of working with you as my destiny.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Burke...")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Heh. I may not have a Core, but I'm proud to have a friend who does.\r\nTests don't mean anything in the real world. I know you have the power and the potential to become the greatest bounty hunter of all time.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Burke, I'm proud to call you family.")


sm.setSpeakerID(9270083)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("My thoughts exactly, Jett.")


sm.sendDelay(1200)


sm.sendNpcController(OBJECT_1, False)
sm.warp(620100020, 0)
