# Created by MechAviv
# Map ID :: 620100043
# Ballroom : Lobby

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.sendDelay(3000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg2/6", 2000, 130, 0, 10, -2, True, 0)
sm.sendDelay(1000)


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("(.......)")


sm.sendDelay(1000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg2/7", 2000, 130, 0, 10, -2, True, 0)
sm.sendDelay(2000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg2/8", 2000, 130, 0, 10, -2, True, 0)
sm.sendDelay(1000)


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("(Ugh... where... am I?)")


sm.sendDelay(500)


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("I don't know a spaceship from a barnacle, but anybody that can survive that kinda fall and still have a thirst for treasure is good in my book.")


sm.sendDelay(500)


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("(Who... are these voices? #b#p9270084##k... my core... )")


sm.sendDelay(1500)


sm.showFieldEffect("newPirate/wakeup2", 0)
sm.sendDelay(7600)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)

sm.warp(620100044, 0)