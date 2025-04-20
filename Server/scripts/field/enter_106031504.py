# id 106031504 (Viking Airship : Galley), field 106031504
sm.lockInGameUI(True, True)
sm.forcedInput(1)
sm.sendDelay(100)
sm.forcedInput(0)
sm.setSpeakerType(3)
sm.setSpeakerID(1302001) # Violetta
sm.setParam(57)
sm.setColor(1)
sm.sendNext("(T-that power, though! Why did I get this key when she could have easily released herself?)")
sm.setParam(33)
sm.sendSay("All right, let's g... Ah, I'm sorry but I can't walk. All this ruckus has shaken me up, plus I've always been a bit on the weaker side. I have no choice but to let you carry me.")
sm.setParam(57)
sm.sendSay("I'd like to formally rej-UGH!")
sm.startQuest(30067)
equips = [1102689, ]
sm.avatarLookSet(equips)
sm.sendDelay(2000)
sm.setParam(33)
sm.sendNext("Leggo!")
equips = [1102689, ]
sm.avatarLookSet(equips)
sm.lockInGameUI(False, True)
sm.warp(106031500)
