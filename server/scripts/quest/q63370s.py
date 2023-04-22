# Created by MechAviv
# Quest ID :: 63370
# Not coded yet

sm.showNpcSpecialActionByTemplateId(2159427, "die", 0)
sm.sendDelay(120)


sm.showNpcSpecialActionByTemplateId(2159427, "die", 0)
sm.sendDelay(1500)


sm.sendDelay(120)


sm.setSpeakerID(2159386)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.sendNext("They just keep coming!")


sm.setSpeakerID(2159382)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.sendSay("We heard there was a hidden lab here, but this is something big!")


sm.setSpeakerID(2159385)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.sendSay("With defenses like these, they must be hiding something big. And I'm going to find out what.")


sm.setSpeakerID(2159382)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.sendSay("You're cool as can be, aren't you Belle? Nothing phases you.")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/2", 900, 0, -120, 0, 2159382, False, 0)
sm.sendDelay(900)


sm.showNpcSpecialActionByTemplateId(2159382, "summon", 0)
sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/12", 1200, 0, -120, 0, 2159382, False, 0)
sm.showNpcSpecialActionByTemplateId(2159382, "catched", 0)
sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 900, 0, -120, 0, 2159386, False, 0)
sm.moveNpcByTemplateId(2159386, False, 30, 100)
sm.sendDelay(2160)


sm.moveNpcByTemplateId(2159386, True, 2, 100)
sm.showNpcSpecialActionByTemplateId(2159383, "summon", 0)
sm.hideUser(False)
sm.forcedInput(2)
sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/13", 900, 0, -120, 0, 2159385, False, 0)
sm.sendDelay(300)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/13", 900, 0, -120, 0, 2159385, False, 0)
sm.sendDelay(900)


sm.sendSessionValue("bell", 2159385)
sm.sendSessionValue("hen", 2159386)
sm.sendSessionValue("sig", 2159383)