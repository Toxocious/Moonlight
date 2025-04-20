# Created by MechAviv
# Map ID :: 940001100
# Heliseum : Heliseum Outskirts

sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Something feels wrong. Am I too late?")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("But even Darmoor himself can't break through the Shield of Heliseum. What happened here?")


sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("The boundary is unmarked...")


sm.moveCamera(False, 600, -600, 178)


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(2638)


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("The Eye of Protector Rock, which has never closed, is dark.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Heliseum has already been captured? What about the shield?")


sm.moveCamera(True, 0, 0, 0)


sm.forcedInput(2)
sm.curNodeEventEnd(True)