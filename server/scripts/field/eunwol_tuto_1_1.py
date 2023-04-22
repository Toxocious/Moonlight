# 927030000
PHANTOM = 2159439
LUMINOUS = 2159467

sm.forcedInput(0)
sm.sendDelay(500)

sm.hideUser(True)
sm.sendDelay(1000)

sm.moveCamera(False, 100, 1000, 18)

sm.removeEscapeButton()
sm.setSpeakerID(PHANTOM)
sm.sendNext("Don't waste your time moping around up here, staring into the distance. It's not as romantic as it seems...")
sm.sendDelay(500)

sm.showNpcSpecialActionByTemplateId(PHANTOM, "teleportation")
sm.sendDelay(840)

sm.removeNpc(PHANTOM)
sm.sendDelay(500)

sm.removeNpc(LUMINOUS)
sm.warp(927030010, 0)
