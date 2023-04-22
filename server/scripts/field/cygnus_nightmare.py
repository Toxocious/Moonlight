# Hidden street : The Nightmare
sm.lockInGameUI(True)
sm.setPlayerAsSpeaker()
sm.sendNext("This must be... oh! It's the queen!")
sm.forcedInput(2)
sm.sendDelay(100)
sm.sendNext("What is she staring at?")
#todo add wz scene for cygnus appearing in mirror
sm.warp(913031002, 0)
sm.startQuest(20893)
sm.lockInGameUI(False)


