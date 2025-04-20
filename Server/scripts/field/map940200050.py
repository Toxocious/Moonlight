MOONBEAM = 3002106
MOONBEAM_CHAT = 3002103
TIGER = 3002104
if sm.hasMobsInField():
    sm.dispose()
else:
    sm.setSpeakerID(2007)
    if sm.sendAskYesNo("Would you like to skip the tutorial cutscenes?"):
        sm.createQuestWithQRValue(37999, "SKIPPED_TIGER")
    if sm.getQRValue(37999) != "SKIPPED_TIGER":
        sm.lockInGameUI(True, False)
        sm.forcedInput(0)

        sm.spawnNpc(MOONBEAM, -600, 20)
        sm.showNpcSpecialActionByTemplateId(MOONBEAM, "summon")

        sm.spawnNpc(TIGER, -500, 20)
        sm.showNpcSpecialActionByTemplateId(TIGER, "summon")
        sm.sendDelay(1500)

        sm.removeEscapeButton()
        sm.flipSpeaker()
        sm.setSpeakerID(MOONBEAM_CHAT)
        sm.sendNext("Hey, let go of me. I said, LEGGO!")

        sm.resetParam()
        sm.removeEscapeButton()
        sm.setSpeakerID(TIGER)
        sm.sendSay("You, my bride. I marry today!")

        sm.flipSpeaker()
        sm.setSpeakerID(MOONBEAM_CHAT)
        sm.sendSay("I'm not gonna marry you! You're not even my type! If only I had my spirit, I'd take you out in one punch!")

        sm.flipDialoguePlayerAsSpeaker()
        sm.sendSay("Moonbeam!")

        sm.setSpeakerID(MOONBEAM_CHAT)
        sm.sendSay("Shade? Oh, Shade! I was so scared! There was a tiger and he was super creepy and I couldn't do anything and it was terrible! *Sob sob*")
        sm.sendDelay(500)

        sm.showBalloonMsgOnNpc("Effect/Direction12.img/effect/tuto/BalloonMsg0/2", 0, 0, -135, TIGER)
        sm.showBalloonMsg("Effect/Direction12.img/effect/tuto/BalloonMsg0/2", 0)
        sm.sendDelay(2000)

        sm.sendNext("...Anyway, save me! I don't wanna get married to this ugly tiger!")

        sm.setSpeakerID(TIGER)
        sm.sendSay("I, hurt. You, all die!")
        sm.sendDelay(500)

        sm.removeNpc(MOONBEAM)
        sm.removeNpc(TIGER)
    sm.spawnMob(9300811, -300, 85, False)
    sm.createQuestWithQRValue(38022, "")
    sm.lockInGameUI(False)
