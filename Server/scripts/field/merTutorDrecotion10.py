# Created by MechAviv
# ID :: [910150001]
# Frozen Fairy Forest : Elluel
if not "1" in sm.getQuestEx(24006, "Eurel"):
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.showEffect("Effect/Direction5.img/effect/mercedesInIce/merBalloon/6", 2000, 0, -100, 0, -2, False, 0)
    sm.sendDelay(2000)


    sm.forcedInput(2)
    sm.setQuestEx(24006, "Eurel", "1")
