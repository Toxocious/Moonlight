# Created by MechAviv
# Map ID :: 940000000
# Hidden Street : Pantheon Vacant Lot

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.spawnNpc(3000110, 350, 0)
sm.showNpcSpecialActionByTemplateId(3000110, "summon", 0)
sm.spawnNpc(3000111, 450, 0)
sm.showNpcSpecialActionByTemplateId(3000111, "summon", 0)
sm.spawnNpc(3000114, 550, 0)
sm.showNpcSpecialActionByTemplateId(3000114, "summon", 0)
sm.spawnNpc(3000119, 700, 0)
sm.showNpcSpecialActionByTemplateId(3000119, "summon", 0)
sm.showEffect("Effect/Direction9.img/effect/story/BalloonMsg2/0", 1200, 0, -120, -2, -2, False, 0)
sm.moveCamera(False, 400, 450, -6)


sm.sendDelay(1500)


sm.setSpeakerID(0)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("They must be plotting something.")


sm.setSpeakerID(3000110)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.sendSay("I need to make up for last time.")


sm.setSpeakerID(3000114)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.sendSay("We were careless. He might be Kaiser, but he's still a kid.")


sm.setSpeakerID(3000119)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.sendSay("We need to plan this out before we do anything.")


sm.showEffect("Effect/Direction9.img/effect/story/BalloonMsg0/2", 1200, 0, -120, 0, 3000110, False, 0)
sm.sendDelay(900)


sm.showEffect("Effect/Direction9.img/effect/story/BalloonMsg1/2", 1200, 0, -120, 0, 3000119, False, 0)
sm.sendDelay(900)


sm.moveCamera(False, 999999999, -85, -6)


sm.setSpeakerID(3000119)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("That's my line, fools! Who are you?")


sm.setSpeakerID(3000119)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.sendSay("Get rid of that pest!")


sm.removeNpc(3000110)
sm.removeNpc(3000111)
sm.removeNpc(3000114)
sm.removeNpc(3000119)
sm.chatScript("Defeat all enemies.")
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)