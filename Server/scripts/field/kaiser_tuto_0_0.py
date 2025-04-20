# Created by MechAviv
# Map ID :: 940001000
# Hidden Street : Great Temple Interior

sm.giveSkill(60001229, 1, 1)
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
OBJECT_1 = sm.sendNpcController(3000107, -600, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000106, 500, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("We're in trouble!")


sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(30)


sm.moveCamera(False, 300, -400, 27)


sm.sendDelay(2501)


sm.showEffect("Effect/Direction9.img/effect/tuto/BalloonMsg1/0", 7000, 0, -150, 0, OBJECT_1, False, 0)
sm.moveNpcByObjectId(OBJECT_1, False, 600, 100)
sm.moveCamera(True, 100, 0, 0)


sm.sendDelay(7810)


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("The capital of Verdant Flora has fallen!")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("It all came down to Darmoor in the end.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Who is left that can stand against Darmoor?")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("We still have the Anima...")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("No, they never measured up to us in military might. They're too passive, as well...they won't take arms against Darmoor unless Darmoor strikes first.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Then, what hope is there?")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Darmoor is surely going to attack our capital, Heliseum. I will head to Heliseum immediately to prepare our defenses.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("But... Is there any way for us to win? Darmoor has defeated another Transcendent and now has power over life and time.")


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(30)


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("At the very least, we can delay him with Heliseum's Shield. The balance of power is not in our favor, but we cannot give up just because things look grim.")


sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(30)


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Cartalion, stay here and protect the people.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("But I want to fight! I'm a warrior of Nova, too.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("We need someone to look after Pantheon, in case we fail. And, we need someone with experience to survive if all of the other commanders fall. You can protect the shield, can't you?")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("...")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Kaiser... Don't push yourself too hard.")


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(30)


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Don't worry, Fenelle. Kaiser does what Kaiser must.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("But...")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
# [FORCED_STAT_RESET] []
sm.warp(940001010, 0)
