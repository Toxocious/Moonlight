ARAN = 2159356
BLACK_MAGE_MINION_1 = 2159360
BLACK_MAGE_MINION_2 = 2159361

sm.removeSkill(20041226)
sm.lockInGameUI(True)
sm.curNodeEventEnd(True)
sm.removeEscapeButton()
sm.forcedInput(0)

sm.spawnNpc(ARAN, 1500, 50)
sm.showNpcSpecialActionByTemplateId(ARAN, "summon", 0)

sm.spawnNpc(BLACK_MAGE_MINION_1, 1350, 50)
sm.showNpcSpecialActionByTemplateId(BLACK_MAGE_MINION_1, "summon", 0)

sm.spawnNpc(BLACK_MAGE_MINION_2, 1300, 50)
sm.showNpcSpecialActionByTemplateId(BLACK_MAGE_MINION_2, "summon", 0)

sm.showEffect("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon0", 1000, 0, 0, -2, -2, False, 0)
sm.sendDelay(1200)

sm.showNpcSpecialActionByTemplateId(ARAN, "attack", 0)
sm.playSound("LuminousTuto/Use2", 100)
sm.sendDelay(450)

sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg0/5", 0, 0, -120, -2, -2, False, 0)
sm.showNpcSpecialActionByTemplateId(BLACK_MAGE_MINION_1, "hit", 0)
sm.showNpcSpecialActionByTemplateId(BLACK_MAGE_MINION_2, "hit", 0)
sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, sm.getNpcObjectIdByTemplateId(BLACK_MAGE_MINION_1), False, 0)
sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, sm.getNpcObjectIdByTemplateId(BLACK_MAGE_MINION_2), False, 0)
sm.playSound("LuminousTuto/Use2", 100)
sm.sendDelay(270)

sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, sm.getNpcObjectIdByTemplateId(BLACK_MAGE_MINION_1), False, 0)
sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, sm.getNpcObjectIdByTemplateId(BLACK_MAGE_MINION_2), False, 0)
sm.playSound("LuminousTuto/Use2", 100)
sm.sendDelay(900)

sm.playSound("LuminousTuto/Use3", 100)
sm.showNpcSpecialActionByTemplateId(BLACK_MAGE_MINION_1, "die", 0)
sm.showNpcSpecialActionByTemplateId(BLACK_MAGE_MINION_2, "die", 0)
sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, sm.getNpcObjectIdByTemplateId(BLACK_MAGE_MINION_1), False, 0)
sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, sm.getNpcObjectIdByTemplateId(BLACK_MAGE_MINION_2), False, 0)
sm.playSound("LuminousTuto/Hit3", 100)
sm.sendDelay(2200)

sm.forcedInput(2)
sm.removeNpc(BLACK_MAGE_MINION_1)
sm.removeNpc(BLACK_MAGE_MINION_2)


