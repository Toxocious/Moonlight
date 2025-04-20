# Created by MechAviv
# Map ID :: 940002020
# Hidden Street : Kaiser's Awakening

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.reservedEffect("Effect/Direction9.img/KaiserTutorial/Scene1")
sm.sendDelay(7200)

sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.warp(940001230, 0)
