# Created by MechAviv
# Map ID :: 940011090
# Eastern Region of Pantheon : Near East Sanctum

sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg0/1", 1200, 30, -70, -2, -2, False, 0)
sm.sendDelay(1200)


sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg0/0", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg0/2", 1200, 0, -120, -2, -2, False, 0)
sm.forcedInput(1)
sm.sendDelay(60)


sm.forcedInput(2)
sm.sendDelay(120)


sm.forcedInput(1)
sm.sendDelay(60)


sm.forcedInput(2)
sm.sendDelay(60)


sm.forcedInput(0)
sm.sendDelay(600)


sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg0/7", 1200, 30, -70, -2, -2, False, 0)
sm.sendDelay(1200)


sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg0/3", 1200, 30, -70, -2, -2, False, 0)
sm.sendDelay(1200)


sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg1/0", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1800)


sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg0/4", 1200, 30, -70, -2, -2, False, 0)
sm.sendDelay(1200)


sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg0/8", 1200, 30, -70, -2, -2, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Hey! Girly. Can you see me?")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("WHAT THE--")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("AH! Relax! I'm Eskalade. I, uh, I live in that bracelet on your pillowy-soft wrist. I was thinking, maybe since we're, like, attached that I'd give you a little of my power.")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Power? What're you talking about?")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Just go over to the spot where you grabbed that relic.")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Fine, but I'm not going to do it just because you told me. I was already going there.")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Fine! How did I end up with such a disobedient little brat?")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I'm not going to take you anywhere if you're going to be rude.")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Who's being rude?! I'm offering you ultimate power in exchange for a little trip to a place you were already going!")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Oh, yeah, I guess that's true.")


sm.forcedInput(2)
sm.curNodeEventEnd(True)
