# Created by MechAviv
# Map ID :: 940001220
# Eastern Region of Pantheon : East Sanctum

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(3000139, -150, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000110, -20, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(3000114, 70, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(3000111, 130, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
OBJECT_5 = sm.sendNpcController(3000115, 250, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
sm.sendSessionValue("enemy0", OBJECT_2)
sm.sendSessionValue("enemy1", OBJECT_3)
sm.sendSessionValue("enemy2", OBJECT_4)
sm.sendSessionValue("enemy3", OBJECT_5)
sm.setSpeakerID(3000110)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Eh? A child dares to interfere?")


sm.setSpeakerID(3000110)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Tear, wait!")


sm.setSpeakerID(3000110)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("These kids are fearless. A pity we cannot allow any witnesses to escape.")


sm.setSpeakerID(3000110)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("You think you can take me down? Bring it!")


sm.setSpeakerID(3000114)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Ha! What can you do all by yourself?")


sm.showEffect("Effect/Direction9.img/effect/story/BalloonMsg1/0", 1200, 0, -120, -2, -2, False, 0)
sm.forcedInput(2)
