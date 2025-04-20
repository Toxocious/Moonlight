# 940200060
MOONBEAM = 3002100

if sm.getQRValue(37999) != "SKIPPED_ILLUST":
    sm.lockInGameUI(True)
    sm.hideUser(False)
    sm.spawnNpc(MOONBEAM, 10, 20)
    sm.showNpcSpecialActionByTemplateId(MOONBEAM, "summon")
    sm.sendDelay(100)

    sm.moveNpcByTemplateId(MOONBEAM, False, 250, 100)
    sm.forcedInput(2)
    sm.sendDelay(4500)

    sm.moveNpcByTemplateId(MOONBEAM, True, 70, 100)
    sm.forcedInput(0)
    sm.sendDelay(2000)

    sm.removeEscapeButton()
    sm.setSpeakerID(MOONBEAM)
    sm.sendNext("Are all humans this slow? Is it because you don't have a tail?")
    sm.sendSay("Ughh! You're too slow. I'm gonna go first. We're almost there and there's nothing too dangerous out here, so just follow me. Just follow this path. Don't go running off, okay? Good human, see you soon!")

    sm.moveNpcByTemplateId(MOONBEAM, False, 510, 100)
    sm.sendDelay(3000)

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("...Wow. Kid has some nerve dragging me to her town then to head off on her own. I might as well follow her, I'm pretty lost here.")

    sm.chatScript("Press the left and right keys to move.")
    sm.removeNpc(MOONBEAM)
sm.createQuestWithQRValue(38000, "")
sm.levelUntil(10)

sm.lockInGameUI(False)
