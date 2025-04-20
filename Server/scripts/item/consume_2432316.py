# Phantheon Return Scroll  |  (2432316)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
if sm.sendAskAccept("Want to go to Pantheon now?\r\n\r\n#r(You will be moved to Pantheon right away if you accept.)"):
    sm.levelUntil(30)

    sm.warp(400000000, 0)
    sm.startQuest(38028)
    sm.completeQuest(38028)
    sm.consumeItem(2432316, 1)
    sm.dispose()
