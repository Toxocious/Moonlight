SEAL_CHECKER = 9300535
SEAL_OF_TIME_1 = 2159363
SEAL_OF_TIME_2 = 2159364
SEAL_OF_TIME_3 = 2159365
SEAL_OF_TIME_4 = 2159366

sm.giveSkill(20041222)
sm.setFuncKeyByScript(True, 20041222, 42)

sm.spawnMob(SEAL_CHECKER, 550, -298, False)
sm.spawnMob(SEAL_CHECKER, 107, -508, False)
sm.spawnMob(SEAL_CHECKER, -195, -508, False)
sm.spawnMob(SEAL_CHECKER, -625, -298, False)

sm.spawnNpc(SEAL_OF_TIME_1, 550, -310)
sm.showNpcSpecialActionByTemplateId(SEAL_OF_TIME_1, "summon", 0)

sm.spawnNpc(SEAL_OF_TIME_2, 107, -520)
sm.showNpcSpecialActionByTemplateId(SEAL_OF_TIME_2, "summon", 0)

sm.spawnNpc(SEAL_OF_TIME_3, -195, -520)
sm.showNpcSpecialActionByTemplateId(SEAL_OF_TIME_3, "summon", 0)

sm.spawnNpc(SEAL_OF_TIME_4, -625, -310)
sm.showNpcSpecialActionByTemplateId(SEAL_OF_TIME_4, "summon", 0)
sm.showFieldEffect("lightning/screenMsg/4")

sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("Time is frozen. I must activate the seals before the Black Mage notices.")
sm.sendSay("I must reach the light on the platform to the right. I can #b<Flash Blink>#k there if I press #r[Shift]#k.")

sm.showFieldEffect("lightning/screenMsg/5")
