MONKEY = 1096003

sm.lockInGameUI(False)
sm.lockInGameUI(True)
sm.forcedInput(4)

sm.removeEscapeButton()

sm.setSpeakerID(MONKEY)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face00")
sm.sendNext("Ook! Ook!")

sm.lockInGameUI(False)