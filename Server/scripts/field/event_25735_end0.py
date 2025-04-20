# Created by MechAviv
# Map ID :: 940000020
# Hidden Street : Pantheon Vacant Lot

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.sendDelay(60)


sm.forcedInput(2)
sm.sendDelay(60)


sm.forcedInput(0)
sm.spawnNpc(3000110, 400, 0)
sm.showNpcSpecialActionByTemplateId(3000110, "summon", 0)
sm.spawnNpc(3000111, 450, 0)
sm.showNpcSpecialActionByTemplateId(3000111, "summon", 0)
sm.spawnNpc(3000114, 500, 0)
sm.showNpcSpecialActionByTemplateId(3000114, "summon", 0)
sm.spawnNpc(3000119, 550, 0)
sm.showNpcSpecialActionByTemplateId(3000119, "summon", 0)
sm.moveCamera(False, 400, 420, 29)


sm.setSpeakerID(0)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Is that all?!")


sm.showEffect("Effect/Direction9.img/effect/story/BalloonMsg0/3", 1200, 0, -120, 0, 3000111, False, 0)
sm.sendDelay(1200)


sm.showEffect("Effect/Direction9.img/effect/story/BalloonMsg1/10", 1200, 0, -120, 0, 3000114, False, 0)
sm.sendDelay(900)


sm.moveCamera(False, 999999999, 3, 29)


sm.moveNpcByTemplateId(3000110, False, 300, 100)
sm.moveNpcByTemplateId(3000111, False, 300, 100)
sm.moveNpcByTemplateId(3000114, False, 300, 100)
sm.moveNpcByTemplateId(3000119, False, 300, 100)
sm.forcedInput(2)
sm.sendDelay(900)


sm.showEffect("Effect/Direction9.img/effect/story/BalloonMsg1/12", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(900)


sm.removeNpc(3000110)
sm.removeNpc(3000111)
sm.removeNpc(3000114)
sm.removeNpc(3000119)
sm.sendDelay(600)


sm.forcedInput(0)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
