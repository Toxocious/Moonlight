# Created by MechAviv
# Map ID :: 620100027
# Spaceship : Spaceship Cockpit

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(1000)


sm.showEffect("Effect/DirectionNewPirate.img/newPirate/balloonMsg1/20", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(2000)

sm.spawnMob(9420567, -378, -120, False)
sm.setSpeakerID(9270085)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("You there! Get away from those controls, and drop that key!")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.chatScript("Eliminate the Key Keeper and find the Master Key.")
sm.startQuestNoCheck(5672)
sm.createQuestWithQRValue(5672, "001")