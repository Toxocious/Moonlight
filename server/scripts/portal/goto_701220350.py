from net.swordie.ms.world.field.fieldeffect import GreyFieldType

BLUE_DEMON = 9310577
GOBLIN_DEMON = 9310578
NINE_TAILED_FOX = 9310579


if "0" in sm.getQRValue(62011):
    sm.warpInstanceIn(701220350)

    sm.lockInGameUI(True, False)
    sm.removeEscapeButton()
    sm.setFieldColour(GreyFieldType.Field, 0, 0, 0, 0)
    sm.showEffectOnPosition("Map/Effect2.img/BlackOut", 23600, 1100, 400)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/1", 2500, GOBLIN_DEMON)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/2", 2500, NINE_TAILED_FOX)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/3", 2500, BLUE_DEMON)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/4", 2500, GOBLIN_DEMON)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/5", 2500, NINE_TAILED_FOX)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/6", 2500, BLUE_DEMON)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/7", 2500, GOBLIN_DEMON)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/8", 2500, BLUE_DEMON)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/9", 2500, NINE_TAILED_FOX)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/10", 2500, BLUE_DEMON)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/Direction_CN.img/effect/tuto/BalloonMsg0/11", 2500, GOBLIN_DEMON)
    sm.sendDelay(2000)

    sm.setFieldColour(GreyFieldType.Field, 250, 250, 250, 1000)
    sm.sendDelay(1000)

    sm.setSpeakerID(NINE_TAILED_FOX)
    sm.setBoxChat()
    sm.sendNext("Intruder!")
    sm.showNpcSpecialActionByTemplateId(NINE_TAILED_FOX, "angry")

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("A human. Gross. Who's got the antihomosapien gel?")

    sm.setSpeakerID(NINE_TAILED_FOX)
    sm.setBoxChat()
    sm.sendNext("Shoo! Begone!")
    sm.showNpcSpecialActionByTemplateId(NINE_TAILED_FOX, "angry")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("Huh? What? Wait a sec--!")

    sm.warpInstanceOut(701220300, 2)
    sm.chatRed("A strong demonic energy shoves you out the door.")
    sm.createQuestWithQRValue(62011, "1")
    sm.lockInGameUI(False)


elif "1" in sm.getQRValue(62011):
    sm.removeEscapeButton()
    sm.setBoxChat()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("What was that? This time, disguise yourself and sneak in...")

    sm.warpInstanceIn(701220350)

    sm.lockInGameUI(True, False)
    sm.hideUser(True)
    sm.sendDelay(1000)

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("Ugh. A human. Thanks a lot, Blue")

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("It's not my fault!")

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("I TOLD you to stop stealing shoes!")

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("But I'm so cute! How could they hold it against me?")

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("Because you're a cliche, dimwit. A demon taking shoes? Of course you're gonna be mad.")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("(These must be the demons stirring up trouble in town.)")

    sm.setSpeakerID(NINE_TAILED_FOX)
    sm.setBoxChat()
    sm.sendNext("Why does the human lurk silently in the portal?")

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("Should I get the human swatter?")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("(You approach the demons slowly, hands up in front of you.)")

    sm.sendDelay(500)

    sm.hideUser(False)
    sm.sendDelay(1000)

    sm.forcedMove(True, 400)
    sm.sendDelay(2000)

    sm.sendNext("Hey, guys. How's it going? Listen, I just--")

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("Ugh. Its breath smells like cow.")

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("Hi, human! Tell me the truth. Are my cheeks pinchable? Are they tasty, like a human baby's?")

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("The human has made a mistake. It has come alone...")

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("Can you burp me, human? And wipe me? I've always wanted--")

    sm.setSpeakerID(NINE_TAILED_FOX)
    sm.setBoxChat()
    sm.sendNext("Silence!")

    sm.sendNext("Human. Why do you invade our home?")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("I don't want any trouble. I'm just looking for a book.")

    sm.setSpeakerID(NINE_TAILED_FOX)
    sm.setBoxChat()
    sm.sendNext("A book...")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("A book written by a monk, a long time ago. Mind if I check the bookshelf?")

    sm.setSpeakerID(NINE_TAILED_FOX)
    sm.setBoxChat()
    sm.sendNext("Mind if I eat your eyeballs?")

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("If you give a human a booksie, they'll ask you for a glass of BLOOD.")

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("Just give it the book! I want to breathe again!")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("(Maybe these demons can be reasoned with. After all, they haven't ripped my heart out yet.)")

    sm.sendNext("All I want is that book. Is there anything I can do to convince you I'm harmless? Maybe a favour?")

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("It thinks it can trick us...")

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("Sing me a lullaby! Make my cradle fall down from a treetop! Ring me around a rosie!!")

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("Sew Blue's mouth shut so he'll stop annoying me.")

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("Foxy, make Gobby be nice!")

    sm.setSpeakerID(NINE_TAILED_FOX)
    sm.setBoxChat()
    sm.sendNext("Actually...")

    sm.sendNext("We accept your proposal.\r\n"
                "Do each of us a favour, and we'll find your book.\r\n"
                "If you fail to complete my, *ahem, OUR, favours, we eat your eyeballs.")

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("Deal! Me, first! Me, first!")

    sm.setSpeakerID(GOBLIN_DEMON)
    sm.setBoxChat()
    sm.sendNext("If the human is going to stick around can we at least make it do something about its breath?")

    sm.lockInGameUI(False)
    sm.warpInstanceOut(701220350)
    sm.setQRValue(62011, "2")


elif sm.hasQuestCompleted(62007) and not "1" in sm.getQRValue(62007):
    sm.warpInstanceIn(701220350)
    sm.lockInGameUI(True, False)
    sm.removeEscapeButton()
    sm.hideNpcByTemplateId(NINE_TAILED_FOX, True)
    sm.hideNpcByTemplateId(GOBLIN_DEMON, True)

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("#fs 24##h0#!!! It's horrible!!! "+ sm.getQRValue(62015) +" and "+ sm.getQRValue(62018) +" have been taken!")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("Taken?! Tell me everything!")

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("A man stormed in here and ook them! I wiggled away, because I'm small and extremely cute. But he took the other two upstairs!")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("A #bman#k? Who would do that?")

    sm.setSpeakerID(BLUE_DEMON)
    sm.setBoxChat()
    sm.sendNext("We have to save "+ sm.getQRValue(62015) +" and "+ sm.getQRValue(62018) +"!!")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("Let's go! Lead the way, "+ sm.getQRValue(62013) +"!")

    sm.warpInstanceOut(701220300, 2)
    sm.createQuestWithQRValue(62007, "1")
    sm.lockInGameUI(False)


elif "2" in sm.getQRValue(62011):
    sm.warp(701220350)