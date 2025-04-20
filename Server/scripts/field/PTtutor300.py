GUARD1 = 9300498
GUARD2 = 9300507

sm.showFieldEffect("phantom/mapname2", 0)

sm.spawnMob(GUARD1, -2050, -1204, False)
sm.spawnMob(GUARD2, -2420, -964, False)
sm.spawnMob(GUARD1, -2070, -717, False)
sm.spawnMob(GUARD2, -2070, -476, False)
sm.spawnMob(GUARD1, -2430, -210, False)

sm.setFuncKeyByScript(True, 20031211, 83)
sm.setFuncKeyByScript(True, 20031212, 79)

sm.removeEscapeButton()
sm.sendSayImage("UI/tutorial/phantom/0/0")
sm.sendSayImage("UI/tutorial/phantom/1/0")
sm.sendSayImage("UI/tutorial/phantom/2/0")
sm.sendSayImage("UI/tutorial/phantom/3/0")
sm.sendSayImage("UI/tutorial/phantom/4/0")
sm.dispose()