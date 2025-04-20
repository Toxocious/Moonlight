# Created by MechAviv
# ID :: [910142051]
# Hidden Street : Harmony

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(4)
OBJECT_1 = sm.sendNpcController(1032209, -15, -30)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.showEffect("Effect/OnUserEff.img/guideEffect/evanTutorial/evanBalloon40", 0, 20, 0, -2, -2, False, 0)
sm.sendDelay(4200)
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene0")


sm.forcedInput(0)
sm.sendDelay(30)


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("How are you feeling?")


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("What happened? I thought... I...")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Who is this Lania, hm? You shouted her name out of the blue and then just conked out.")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("She's nobod--She's a girl I met when I woke up in the present world. We lived together for a few years before the Dark awoke inside me.")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I see. And this darkness... You got it from your fight against the Black Mage?")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Yes. I believe it happened when I sealed him away. I'm not sure if it's a piece of him that I've absorbed, or some part of me I never realized I had.")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("There's something I gotta tell you, Luminous. I did do a bit of research on Dark magic in the last few centuries, and I found that Light and Dark are two sides of the same coin. That's why the darkness got into you so easily!")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Where have I heard that before...?")


sm.setSpeakerID(1032209)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I don't know, but Dark grows stronger when Light fades. But since you have BOTH powers, I bet you could learn to control your darkness! Hold on a tick...")

sm.startQuest(25587)
sm.completeQuest(25587)
sm.giveExp(10000)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.sendNpcController(OBJECT_1, False)
sm.warp(101000200, 0)