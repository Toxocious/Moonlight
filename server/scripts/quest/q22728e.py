# Created by MechAviv
# Kinesis Introduction
KINESIS = 1531000

sm.lockForIntro()
sm.setIntroBoxChat(KINESIS)
sm.sendNext("Geez, they just don't quit!")
sm.sendSay("I'm not seeing anything dangerous here. I should move on.")

sm.blind(1, 255, 0, 500)
sm.sendDelay(500)

sm.warp(331002500, 0)