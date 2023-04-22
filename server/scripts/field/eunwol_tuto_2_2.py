# 927030010
sm.forcedInput(0)
sm.sendDelay(100)

sm.hideUser(True)
sm.blind(1, 200, 0, 1000)
sm.sendDelay(1000)

sm.sayMonologue("\r\n\r\n\r\nAran felt uneasy.", False)
sm.sayMonologue("\r\n\r\nSomething made her think that perhaps this was the last time she would see her friend. Was it intuition? Or maybe an omen.", True)

sm.createQuestWithQRValue(38900, "1")
sm.warp(927030050, 0)
