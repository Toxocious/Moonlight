sm.lockInGameUI(True)

sm.giveAndEquip(1352104)
sm.giveSkill(20031211, 0)
sm.giveSkill(20031211, 1)
sm.giveSkill(20031212, 0)
sm.giveSkill(20031212, 1)
sm.playVideoByScript("phantom_memory.avi")

sm.showFieldEffect("phantom/mapname1", 0)
sm.forcedInput(1)
sm.sendDelay(1000)

sm.forcedInput(0)
sm.sendDelay(1000)

sm.forcedInput(2)
sm.sendDelay(1000)

sm.forcedInput(0)
sm.sendDelay(1000)

sm.forcedInput(1)
sm.avatarOriented("Effect/OnUserEff.img/questEffect/phantom/tutorial")
sm.sendDelay(1000)

sm.forcedInput(0)
sm.sendDelay(1000)

sm.forcedInput(2)
sm.sendDelay(1000)

sm.forcedInput(0)
sm.sendDelay(1000)

sm.forcedInput(1)
sm.sendDelay(500)

sm.forcedInput(0)
sm.sendDelay(1000)

sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("I believe it's time to make an appearance.")
sm.sendSay("My heart is racing! It's been ages since I've felt so alive. Or anxious. I am terribly anxious.")
sm.sendSay("If I stand here any longer, I'll lose the nerve. It's now or never!")

sm.lockInGameUI(False)
sm.warpInstanceIn(915000100, 1)
sm.dispose()
