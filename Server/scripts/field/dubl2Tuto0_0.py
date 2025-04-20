sm.lockInGameUI(False)
sm.progressMessageFont(3, 20, 20, 0, "You can start the quest by clicking the NPC with the lightbulb over their head.")
sm.blindEffect(False)

if sm.sendAskAccept("Would you like to skip the introduction?"):
    sm.levelUntil(10)
    sm.jobAdvance(400)
    sm.resetAP(chr.getJob())
    sm.warp(101000000)
    sm.giveItem(1332063)
    sm.dispose()
