# 940200011
MOONBEAM = 3002100

if sm.hasQuest(38004):
    sm.setSpeakerID(2007)
    if sm.sendAskYesNo("Would you like to skip the tutorial cutscenes?"):
        sm.createQuestWithQRValue(37999, "SKIP_38004")
    if sm.getQRValue(37999) != "SKIP_38004":
        sm.lockInGameUI(True)
        sm.spawnNpc(MOONBEAM, 204, -135)
        sm.showNpcSpecialActionByTemplateId(MOONBEAM, "summon")
        sm.forcedInput(0)
        sm.sendDelay(2000)

        sm.removeEscapeButton()
        sm.setSpeakerID(MOONBEAM)
        sm.sendNext("Um, Fox God? This is Shade, who will be hanging out with us. I gotta say, he has no ears or tails, but he's still pretty cool. So make sure he doesn't get eaten or anything, okay?")

        sm.flipDialoguePlayerAsSpeaker()
        sm.sendSay("(It's interesting that the fox people believe in a fox god. Of course, when I say it like that it seems super obvious.)")

        sm.setSpeakerID(MOONBEAM)
        sm.sendSay("Shade! Hey, stop fooling around and start praying to the fox god! Tell him to watch over you! Hurry!")

        sm.flipDialoguePlayerAsSpeaker()
        sm.sendSay("W-watch over me... ")

        sm.playSound("eunwolTuto/foxgod", 100)
        sm.showBalloonMsgOnNpc("Effect/Direction15.img/effect/tuto/foxgod/stone", 0, 0, 0, MOONBEAM)
        sm.sendDelay(2100)

        sm.playSound("eunwolTuto/blessing", 100)
        sm.avatarOriented("Effect/Direction15.img/effect/tuto/foxgod/eunwol")
        sm.sendDelay(2500)

        sm.sendNext("What was that? Something shiny covered my body...!")

        sm.setSpeakerID(MOONBEAM)
        sm.sendSay("What's the fuss? The fox god sent you his blessing. Go on, say thank you! The fox god is being very generous, giving blessings to the earless and tailless.")

        sm.flipDialoguePlayerAsSpeaker()
        sm.sendSay("T-thank you. Wait, so that light was the fox god's blessing? You mean to say the fox god's blessing is an actual power?")

        sm.setSpeakerID(MOONBEAM)
        sm.sendSay("What, you thought I was joking? That thing that just went POP into your body was the #rpower of spirits#k. You can use that power now, and I'll teach you all the ins and outs of it.")

        sm.removeNpc(MOONBEAM)
    sm.giveSkill(20051284)
    sm.giveSkill(20050285)
    sm.completeQuest(38004)
    sm.addLevel(1)
    sm.chatScript("You learned [Fox Trot] and [Spirit Bond 1].")
    if sm.getQRValue(37999) != "SKIP_38004":
        sm.sendDelay(1000)

    sm.lockInGameUI(False)
    sm.warp(410000002, 0)
elif sm.hasQuest(38011):
    sm.setSpeakerID(2007)
    if sm.sendAskYesNo("Would you like to skip the tutorial cutscenes?"):
        sm.createQuestWithQRValue(37999, "SKIP_38011")
    if sm.getQRValue(37999) != "SKIP_38011":
        sm.lockInGameUI(True)
        sm.spawnNpc(MOONBEAM, 204, -135)
        sm.showNpcSpecialActionByTemplateId(MOONBEAM, "summon")
        sm.forcedInput(0)
        sm.sendDelay(2000)

        sm.removeEscapeButton()
        sm.setSpeakerID(MOONBEAM)
        sm.sendNext("...Why did you follow me?")

        sm.flipDialoguePlayerAsSpeaker()
        sm.sendSay("......")

        sm.setSpeakerID(MOONBEAM)
        sm.sendSay("Dummy, this place is dangerous. It's filled with tigers all around.")

        sm.flipDialoguePlayerAsSpeaker()
        sm.sendSay("...I'm sorry.")

        sm.setSpeakerID(MOONBEAM)
        sm.sendSay("Hmph, what for?\r\n\r\n#b#L0# Sorry I didn't eat the raw liver#l\r\n#L1# Sorry I lied about eating the raw liver#l\r\n#L2# Sorry for getting hurt and worrying you#l")

        sm.sendNext("I-I don't care about you! You're stupid, and dumb, and... And you have no ears, oddly big, and you can't even hunt well! What else... ")

        sm.flipDialoguePlayerAsSpeaker()
        sm.sendSay("I'm sorry.")

        sm.setSpeakerID(MOONBEAM)
        sm.sendSay("You know, I was the one that got the liver for you. All by myself, from all the way in the deep parts of the forest... But why did you get these things for everyone? Getting hurt like that just for some stupid mouse tails. Shade, you dummy!")

        sm.flipDialoguePlayerAsSpeaker()
        sm.sendSay("Yes, I'm sorry.")

        sm.setSpeakerID(MOONBEAM)
        sm.sendSay("...I still think you're too weak even with a spirit. You can't run, can't claw anyone, or even bite anyone with fangs. You'd have no chance against a tiger if you met one. So... I'll give you my spirit.")

        sm.sendDelay(500)

        sm.playSound("eunwolTuto/rang", 100)
        sm.showBalloonMsgOnNpc("Effect/Direction15.img/effect/tuto/passon/rang", 0, 0, 0, MOONBEAM)
        sm.sendDelay(1000)

        sm.playSound("eunwolTuto/eunwol", 100)
        sm.avatarOriented("Effect/Direction15.img/effect/tuto/passon/eunwol")
        sm.sendDelay(2300)

        sm.flipDialoguePlayerAsSpeaker()
        sm.sendNext("Moonbeam! What are you...!")
        sm.sendSay("......")

        sm.setSpeakerID(MOONBEAM)
        sm.sendSay("I wanna start heading back first. ...I might be slower than you now. See ya!")

        sm.removeNpc(MOONBEAM)

        sm.flipDialoguePlayerAsSpeaker()
        sm.sendSay("Why are you doing this to me? Maaaaan, Silver's going to be spitting fire when he finds out...")

    chr.addSkill(25001002, chr.getSkillLevel(25001000), 25)
    chr.removeSkillAndSendPacket(25001000)
    sm.giveExp(1800)
    sm.giveAndEquip(1353100)
    sm.completeQuest(38011)
    sm.chatScript("[Swift Strike] was upgraded to [Flash Fist].")
    if sm.getQRValue(37999) != "SKIP_38011":
        sm.sendDelay(1000)

    sm.lockInGameUI(False)
    sm.warp(410000002, 0)
