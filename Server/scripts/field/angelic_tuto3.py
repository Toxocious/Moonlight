# Created by MechAviv
# Map ID :: 940011030
# Eastern Region of Pantheon : East Sanctum

# [SET_DRESS_CHANGED] [00 00 ]
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(3000140, -300, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000104, -450, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(3000110, -120, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(3000114, -50, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
OBJECT_5 = sm.sendNpcController(3000111, 130, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
OBJECT_6 = sm.sendNpcController(3000115, 250, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_6, "summon", 0)
sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Nothing here, big surprise...")


sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/0", 1200, 0, -120, 0, OBJECT_2, False, 0)
sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/0", 1200, 0, -120, 0, OBJECT_1, False, 0)
sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/3", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Hey, who are those priests? I've never seen 'em before.")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Velderoth, this isn't right!")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You're right. They look suspicious. I'm going to run back to base and get help. You two stay here and keep an eye on them, okay? But no heroics. You get out of here if they spot you.")


sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg0/0", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(900)


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("What are they talking about?")


sm.sendNpcController(OBJECT_2, False)
sm.setSpeakerID(3000110)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("The relic's disappearance should weaken the shields.")


sm.setSpeakerID(3000114)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I thought the relic was cursed... should we really be touching it?")


sm.setSpeakerID(3000110)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I did not realize they allowed superstitious nincompoops entry to our order! Will you balk at the call of destiny?")


sm.setSpeakerID(3000110)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("(Are they trying to steal the relic?)")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("They're gonna take the relic away!")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Let's stop them!")


sm.moveNpcByObjectId(OBJECT_1, False, 300, 100)
sm.sendDelay(300)


sm.forcedInput(2)
sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/1", 1200, 0, -120, 0, OBJECT_3, False, 0)
sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/1", 1200, 0, -120, 0, OBJECT_4, False, 0)
sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/1", 1200, 0, -120, 0, OBJECT_5, False, 0)
sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/1", 1200, 0, -120, 0, OBJECT_6, False, 0)
sm.sendDelay(300)


sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/6", 900, 0, -120, -2, -2, False, 0)
sm.sendDelay(300)


sm.showFieldEffect("kaiser/tear_rush", 0)
