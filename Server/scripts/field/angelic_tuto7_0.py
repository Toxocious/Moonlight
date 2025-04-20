# Created by MechAviv
# Map ID :: 940011070
# Pantheon : Great Temple Interior

OBJECT_1 = sm.sendNpcController(3000152, 300, 10)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.setSpeakerID(3000152)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("#h0#, wait!")


sm.forcedInput(2)
sm.sendDelay(60)


sm.forcedInput(0)
sm.setSpeakerID(3000152)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("This 'curse' is not as ominous as it sounds. That relic has never reacted to any other priest, yet it clung to you like a nurturing mother.")


sm.setSpeakerID(3000152)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("But I don't want any of this! I didn't mean to take anything!")


sm.forcedInput(1)
sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg0/6", 1200, 0, -120, 0, OBJECT_1, False, 0)
sm.curNodeEventEnd(True)
