# Hidden Street : Black Mage's Antechamber (927020060)  |  Used in Luminous' Tutorial

sm.lockInGameUI(True)
sm.curNodeEventEnd(True)

sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("Looks like Freud and Mercedes are already inside. "
            "I hope I'm not too late.")
sm.sendDelay(750)

sm.curNodeEventEnd(True)
sm.forcedInput(2)
