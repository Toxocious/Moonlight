BALROG_SUMMON = 1096011
BALROG = 1096008
SKIPPER = 1096000
REITING = 1096001
SKIPPER_AND_REITING = 1096002

sm.spawnNpc(BALROG_SUMMON, 2000, -20)
sm.showNpcSpecialActionByTemplateId(BALROG_SUMMON, "summon", 0)

sm.showEffect("Effect/Summon.img/15", 0, 0, 0, 0, sm.getNpcObjectIdByTemplateId(BALROG_SUMMON), False, 0)
sm.playSound("cannonshooter/summon", 100)
sm.sendDelay(2000)

sm.removeNpc(SKIPPER)
sm.removeNpc(REITING)

sm.spawnNpc(BALROG, 2000, -20)
sm.showNpcSpecialActionByTemplateId(BALROG, "summon", 0)

sm.spawnNpc(SKIPPER_AND_REITING, 2108, -82)
sm.showNpcSpecialActionByTemplateId(SKIPPER_AND_REITING, "summon", 0)

sm.showEffect("Effect/Direction4.img/effect/cannonshooter/balog/0", 2000, 0, -200, 0, sm.getNpcObjectIdByTemplateId(BALROG), False, 0)
sm.sendDelay(500)

sm.showNpcSpecialActionByTemplateId(BALROG, "attack2", 0)
sm.sendDelay(1000)

sm.showEffect("Effect/Direction4.img/effect/cannonshooter/npc/0", 2000, 0, -169, 0, sm.getNpcObjectIdByTemplateId(SKIPPER_AND_REITING), False, 0)
sm.showNpcSpecialActionByTemplateId(BALROG, "attack1", 0)
sm.sendDelay(1000)

sm.playSound("Party1/Failed", 100)
sm.showEffect("Effect/Direction4.img/effect/cannonshooter/User/0", 2000, 0, -100, 0, -2, False, 0)
sm.sendDelay(1000)

sm.forcedInput(1)
sm.sendDelay(150)

sm.forcedInput(0)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face02")
sm.sendDelay(1000)

sm.showNpcSpecialActionByTemplateId(BALROG, "attack2", 0)
sm.showEffect("Effect/Direction4.img/effect/cannonshooter/npc/1", 2000, 0, -169, 0, sm.getNpcObjectIdByTemplateId(SKIPPER_AND_REITING), False, 0)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face02")
sm.sendDelay(500)

sm.showEffect("Effect/Direction4.img/effect/cannonshooter/User/1", 2000, 0, -100, 0, -2, False, 0)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face05")
sm.sendDelay(1000)

sm.showNpcSpecialActionByTemplateId(BALROG, "attack2", 0)
sm.playSound("cannonshooter/Attack1", 100)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face05")
sm.sendDelay(100)

sm.reservedEffect("Effect/Direction4.img/cannonshooter/face05")
sm.sendDelay(1000)

sm.showNpcSpecialActionByTemplateId(BALROG, "attack1", 0)
sm.showEffect("Effect/Direction4.img/effect/cannonshooter/balog/0", 2000, 0, -200, 0, sm.getNpcObjectIdByTemplateId(BALROG), False, 0)
sm.showEffect("Mob/8150000.img/attack2/info/effect", 0, 0, 0, 0, sm.getNpcObjectIdByTemplateId(BALROG), False, 0)
sm.playSound("cannonshooter/Attack1", 100)
sm.sendDelay(1000)

sm.showEffect("Mob/8150000.img/attack2/info/hit", 0, 0, 0, 0, 0, False, 0)
sm.showEffect("Effect/Direction4.img/effect/cannonshooter/User/2", 2000, 0, -100, 0, -2, False, 0)
sm.forcedInput(6)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face02")
sm.sendDelay(500)

sm.forcedInput(0)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face02")
sm.sendDelay(500)

sm.showNpcSpecialActionByTemplateId(BALROG, "attack2", 0)
sm.showEffect("Mob/8150000.img/attack1/info/effect", 0, 0, 0, 0, sm.getNpcObjectIdByTemplateId(BALROG), False, 0)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face02")
sm.sendDelay(500)

sm.showEffect("Mob/8150000.img/attack1/info/hit", 0, 0, 0, 0, 0, False, 0)
sm.playSound("cannonshooter/Attack1", 100)
sm.ballonMsg("Why are you acting like this?")
sm.forcedInput(6)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face01")
sm.sendDelay(500)

sm.showEffect("Mob/8150000.img/attack1/info/effect", 0, 0, 0, 0, sm.getNpcObjectIdByTemplateId(BALROG), False, 0)
sm.forcedInput(2)
sm.sendDelay(200)

sm.forcedInput(1)
sm.sendDelay(200)

sm.forcedInput(2)
sm.sendDelay(200)

sm.forcedInput(1)
sm.sendDelay(200)

sm.forcedInput(2)
sm.sendDelay(150)

sm.forcedInput(0)
sm.sendDelay(500)

sm.showNpcSpecialActionByTemplateId(BALROG, "attack", 0)
sm.playSound("cannonshooter/Attack2", 100)
sm.showEffect("Effect/Direction4.img/effect/cannonshooter/balog/1", 2000, 0, -200, 0, sm.getNpcObjectIdByTemplateId(BALROG), False, 0)
sm.sendDelay(300)

sm.forcedInput(2)
sm.showEffect("Effect/Direction4.img/effect/cannonshooter/User/3", 2000, 0, -100, 0, -2, False, 0)
sm.reservedEffect("Effect/Direction4.img/cannonshooter/face02")
sm.sendDelay(2000)

sm.showNpcSpecialActionByTemplateId(BALROG, "stand", 0)
sm.removeNpc(BALROG)
sm.removeNpc(SKIPPER_AND_REITING)
sm.removeNpc(BALROG_SUMMON)
sm.warp(912060100, 0)