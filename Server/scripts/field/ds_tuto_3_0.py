FERDI = 2159311

sm.lockInGameUI(True)
sm.levelUntil(10)

sm.sendDelay(3000)

sm.showFieldEffect("demonSlayer/text12", 0)
sm.sendDelay(5000)

sm.forcedInput(1)
sm.sendDelay(10)

sm.forcedInput(0)
sm.removeEscapeButton()
sm.setSpeakerID(FERDI)
sm.setPlayerAsSpeaker()
sm.sendNext("........")

sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg0/14", 2000, 130, 50, 10, 0, True, 0)
sm.sendDelay(2000)

sm.sendNext("(I think I hear something...)")

sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg0/15", 2000, -130, 50, 10, 0, True, 0)
sm.sendDelay(2000)

sm.sendNext("(Where am I? Am I still alive...?)")

sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg0/16", 2000, 130, 50, 10, 0, True, 0)
sm.sendDelay(2000)

sm.sendNext("(Ugh... My energy... Something is stealing my energy!)")

sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg0/17", 2000, -130, 50, 10, 0, True, 0)
sm.sendDelay(2000)

sm.sendNext("(I must escape before they drain all my power!)")

sm.chatScript("Tap the Control Key repeatedly to break the wall.")
sm.showEffect("Effect/Direction6.img/effect/tuto/guide1/0", 3000, 0, -100, 20, 0, True, 0)
sm.patternInputRequest("17#17#17#", 2, 2, 3000)

sm.playSound("demonSlayer/punch", 100)
sm.playSound("demonSlayer/crackEgg", 100)
sm.chatScript("Tap the Control Key repeatedly to break the wall.")
sm.showEffect("Effect/Direction6.img/effect/tuto/guide1/0", 3000, 0, -100, 20, 0, True, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/breakEgg/0", 6600, 0, 0, 0, 0, True, 0)
sm.patternInputRequest("17#17#17#", 2, 2, 3000)

sm.playSound("demonSlayer/punch", 100)
sm.playSound("demonSlayer/crackEgg", 100)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg0/7", 2000, 130, 100, 10, 0, True, 0)
sm.chatScript("Tap the Control Key repeatedly to break the wall.")
sm.showEffect("Effect/Direction6.img/effect/tuto/guide1/0", 3000, 0, -100, 20, 0, True, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/breakEgg/0", 6600, 0, 0, 0, 0, True, 0)
sm.patternInputRequest("17#17#17#", 2, 2, 3000)

sm.playSound("demonSlayer/punch", 100)
sm.playSound("demonSlayer/crackEgg", 100)
sm.chatScript("Tap the Control Key repeatedly to break the wall.")
sm.showEffect("Effect/Direction6.img/effect/tuto/guide1/0", 3000, 0, -100, 20, 0, True, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/breakEgg/1", 6600, 0, 0, 1, 0, True, 0)
sm.patternInputRequest("17#17#17#", 2, 2, 3000)

sm.playSound("demonSlayer/punch", 100)
sm.playSound("demonSlayer/crackEgg", 100)
sm.chatScript("Tap the Control Key repeatedly to break the wall.")
sm.showEffect("Effect/Direction6.img/effect/tuto/guide1/0", 3000, 0, -100, 20, 0, True, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/breakEgg/1", 6600, 0, 0, 1, 0, True, 0)
sm.patternInputRequest("17#17#17#", 2, 2, 3000)

sm.playSound("demonSlayer/punch", 100)
sm.playSound("demonSlayer/crackEgg", 100)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/1", 2000, -130, 50, 10, 0, True, 0)
sm.playSound("demonSlayer/crackEgg", 100)
sm.sendDelay(1000)

sm.showEffect("Effect/Direction6.img/effect/tuto/breakEgg/2", 9000, 0, 0, 5, 0, True, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/2", 2000, 130, 50, 10, 0, True, 0)
sm.sendDelay(1000)

sm.playSound("demonSlayer/crackEgg", 100)
sm.showFieldEffect("demonSlayer/whiteOut", 0)
sm.warpInstanceIn(931050020, 0)