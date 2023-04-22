# Created by MechAviv
# Map ID :: 940011050
# Eastern Region of Pantheon : East Sanctum

# [SET_DRESS_CHANGED] [00 00 ]
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(4)
OBJECT_1 = sm.sendNpcController(3000141, -150, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")
sm.sendDelay(1200)

OBJECT_2 = sm.sendNpcController(3000104, -380, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.moveNpcByObjectId(OBJECT_2, False, 100, 100)
sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg1/5", 900, 0, -120, 0, OBJECT_2, False, 0)
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")
sm.sendDelay(900)

sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/0", 1200, 0, -120, 0, OBJECT_2, False, 0)
OBJECT_3 = sm.sendNpcController(3000106, -330, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(3000107, -420, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")
sm.sendDelay(900)

sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/0", 1200, 0, -120, 0, OBJECT_3, False, 0)
sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/0", 1200, 0, -120, 0, OBJECT_4, False, 0)
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")
sm.sendDelay(900)

sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("What's going on here?")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Kyle! #h0#!")


sm.showEffect("Effect/BasicEff.img/Kaiser_Transform4_S", 0, 0, 0, 0, OBJECT_1, False, 0)
sm.sendDelay(900)


sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg1/6", 1200, 0, -120, 0, OBJECT_3, False, 0)
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")
sm.sendDelay(300)

sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg1/7", 1200, 0, -120, 0, OBJECT_4, False, 0)
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")
sm.sendDelay(600)


sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg0/0", 1200, 0, -120, 0, OBJECT_2, False, 0)
sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")
sm.sendNext("Kaiser has finally made himself known. Cartalion, please take these two to the clinic.")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene3")
sm.warp(940011060, 0)