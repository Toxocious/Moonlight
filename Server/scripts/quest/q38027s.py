MOONBEAM = 3002000

sm.setSpeakerID(2007)
if sm.sendAskYesNo("Would you like to skip the tutorial cutscenes?"):
    sm.createQuestWithQRValue(37999, "SKIP_38027")

if sm.getQRValue(37999) != "SKIP_38027":
    sm.lockInGameUI(True)
    sm.forcedInput(0)

    sm.removeEscapeButton()
    sm.setSpeakerID(MOONBEAM)
    sm.sendNext("Y'know, I always knew. That you'd be leaving someday. Your scent is of the winds.")

    sm.setPlayerAsSpeaker()
    sm.sendSay("......")

    sm.setSpeakerID(MOONBEAM)
    sm.sendSay("I gave you my spirit because... Because I didn't want you to leave. You promised that you'd be by my side to protect me. So. So...")

    sm.setPlayerAsSpeaker()
    sm.sendSay("...I had a friend. The first person to ever lend a hand to me. I told him I didn't have a reason to live, and he told me I don't have a reason to die, either. He also promised we'd get through the tough times together.")
    sm.sendSay("Before I... came here, his face was the last one I saw. He's probably in pain. Thinking that I'm gone. And he probably feels like it's his fault.")

    sm.setSpeakerID(MOONBEAM)
    sm.sendSay("......")

    sm.setPlayerAsSpeaker()
    sm.sendSay("I must go back. To those who are waiting for me. To those in pain. I must go tell them I'm alive, and to protect the world we love. And when that's all over... I'll come back.")

    sm.setSpeakerID(MOONBEAM)
    sm.sendSay("...I'm your friend too, right? I'm important too, right? Like them?")

    sm.setPlayerAsSpeaker()
    sm.sendSay("Of course.")

    sm.setSpeakerID(MOONBEAM)
    sm.sendSay("Us foxes call those companions. You and I are companions, so you have to come back. I'll miss you so much. It's gonna be so hard on me. So come back soon, okay?")
    sm.sendSay("Wait, you're stupid so you might forget all this. Here...")
    sm.sendDelay(500)

    sm.showEffect("Effect/Direction15.img/effect/story/tatoo/0", 0, -54, -300)
    sm.sendDelay(2400)

sm.startQuest(38901)

if sm.getQRValue(37999) != "SKIP_38027":
    sm.sendDelay(1000)

    sm.sendNext("This is a mark of companionship. We made our promise under the Fox Tree, so you have to keep your promise. Otherwise the fox god is gonna be really mad!")
    sm.sendSay("Ah! I'm really hungry all of a sudden. I'm gonna head home first. Catch me if you can, slowpoke!")
    sm.sendDelay(500)

sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(6000)

if sm.getQRValue(37999) != "SKIP_38027":
    sm.setPlayerAsSpeaker()
    sm.sendNext("The rain... It's not stopping.")
sm.lockInGameUI(False)
