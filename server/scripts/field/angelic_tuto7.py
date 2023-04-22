# Created by MechAviv
# Map ID :: 940011070
# Pantheon : Great Temple Interior

# [SET_DRESS_CHANGED] [00 00 ]
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(1)
sm.sendDelay(60)


sm.forcedInput(0)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("I-I stole something! I've never stolen anything. I didn't mean to, I swear!")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I don't even have any MP... I'm incapable of doing anything... I'm useless.... ")


sm.forcedInput(1)
sm.sendDelay(120)


sm.reservedEffect("Effect/Direction10.img/angelicTuto/Scene1")
sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg0/5", 1200, 0, -120, -2, -2, False, 0)
