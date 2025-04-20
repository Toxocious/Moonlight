ARKARIUM = 2159309
MOB = 9300455

sm.forcedAction(371, 0)
sm.playSound("demonSlayer/31121001", 100)
sm.showEffect("Skill/3112.img/skill/31121001/effect", 0, 340, 71)
sm.showNpcSpecialActionByTemplateId(ARKARIUM, "teleportation", 0)
sm.sendDelay(570)

sm.removeNpc(ARKARIUM)
sm.sendDelay(870)

sm.spawnNpc(ARKARIUM, 500, 50)
sm.showNpcSpecialActionByTemplateId(ARKARIUM, "summon", 0)

sm.removeEscapeButton()
sm.setSpeakerID(ARKARIUM)
sm.sendNext("You disappoint me! You don't even understand the Black Mage's true goal. Guards! Eliminate the betrayer!")

sm.lockInGameUI(False)
sm.chatScript("Eliminate all guards.")
sm.playSound("demonSlayer/summonGuard", 100)

sm.spawnMob(MOB, 450, 71, False)
sm.spawnMob(MOB, 400, 71, False)
sm.spawnMob(MOB, 350, 71, False)
sm.startQuestNoCheck(23205)
sm.reservedEffect("Effect/Direction6.img/DemonTutorial/Scene4")
