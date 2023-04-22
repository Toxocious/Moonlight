# Created by MechAviv
# Map ID :: 620100040
# Ballroom : Lobby

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.showFieldEffect("newPirate/Shuttle/0", 0)
sm.sendDelay(4200)


sm.showFieldEffect("newPirate/TimeTravel/0", 0)
sm.sendDelay(3500)


sm.showFieldEffect("newPirate/text1", 0)
sm.sendDelay(1500)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)

sm.warp(620100041, 0)
