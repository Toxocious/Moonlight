LANIA = 1032200

sm.lockInGameUI(True)
sm.curNodeEventEnd(True)
sm.forcedInput(4)
sm.showEffect("Effect/OnUserEff.img/guideEffect/evanTutorial/evanBalloon401", 0, 0, 20, -2, -2, False, 0)
sm.spawnNpc(LANIA, 800, -40)
sm.showNpcSpecialActionByTemplateId(LANIA, "summon", 0)
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene0")
sm.sendDelay(1000)

sm.moveNpcByTemplateId(LANIA, True, 200, 100)
sm.moveCamera(False, 400, 540, -230)

sm.removeEscapeButton()
sm.setSpeakerID(LANIA)
sm.sendNext("Is... is that a person?!")

sm.moveNpcByTemplateId(LANIA, True, 600, 100)

sm.moveCamera(True, 100, 0, 0)

sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene0")
sm.sendDelay(0)
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene0")
sm.sendDelay(0)
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene0")
sm.sendDelay(0)
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene0")
sm.sendDelay(0)
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene0")
sm.sendDelay(0)
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene0")
sm.sendDelay(0)

sm.forcedInput(0)
sm.forcedAction(4, 2000)
sm.showEffect("Effect/Direction5.img/effect/mercedesInIce/merBalloon/1", 2000, 20, -100, -2, -2, False, 0)
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene2")
sm.sendDelay(2000)

sm.sendNext("Oh, thank goodness you're not dead! You're gonna be okay. This is a small forest located near #bEllinia#k. My name is Lania.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("What is this place? Ellinia? Take me to #bElluel#k.")

sm.setSpeakerID(LANIA)
sm.sendSay("Elluel? I think I heard that in a fairy tale one time...")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Where is the Black Mage? Has he invaded your town?")

sm.setSpeakerID(LANIA)
sm.sendSay("Huh? I heard a story about five heroes defeating some evil old wizard called the Black Mage hundreds of years ago, but I think that was just a fairy tale too...")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("(This is madness. Could hundreds of years have passed by in the blink of an eye?!)\r\nI feel...odd.")

sm.setSpeakerID(LANIA)
sm.sendSay("Hey, whoa, not on me!")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("I-I will be all right. Are you not too young to wander the woods alone? Where are your parents?")

sm.setSpeakerID(LANIA)
sm.sendSay("I... don't know. Me and Penny have been here for as long as I can remember....")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("I apologize. I meant no offense.")

sm.setSpeakerID(LANIA)
sm.sendSay("It's okay! Penny's my best pal. She's also a cat, but at least she's pretty good at talking. I kinda miss talking to people though. Penny always just nags me.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("I fear I am poor company. I've never been much for small talk.")

sm.setSpeakerID(LANIA)
sm.sendSay("For somebody that's bad at words, you sure use a lot of big ones! Why don't you come back to my house? Me and Penny can make you something to eat.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("(If the Black Mage is truly gone, perhaps I could find meaning in a simple life.)\r\nY-yes, I will accompany you. If it is not too much trouble.")

sm.moveNpcByTemplateId(LANIA, False, 600, 100)
sm.sendDelay(1000)

sm.forcedInput(2)
sm.sendDelay(1000)

sm.removeNpc(LANIA)
sm.lockInGameUI(False)
sm.warp(910141050, 0)