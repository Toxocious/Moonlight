# Created by MechAviv
# Map ID :: 940001150
# Unknown : Unknown

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
OBJECT_1 = sm.sendNpcController(3000131, -390, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000122, -740, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(3000125, -640, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.sendDelay(1500)


sm.forcedAction(474, 0)
sm.showEffect("Skill/6112.img/skill/61121100/effect", 0, 0, 0, -2, -2, False, 0)
sm.playSound("Kaiser/61121100", 100)
sm.sendDelay(150)


sm.showNpcSpecialActionByObjectId(OBJECT_2, "die1", 0)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "die1", 0)
sm.showEffect("Skill/6112.img/skill/61121100/hit", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.showEffect("Skill/6112.img/skill/61121100/hit", 0, 0, 0, 0, OBJECT_3, False, 0)
sm.sendDelay(1400)
sm.playExclSoundWithDownBGM("Skill.img/61121100/Hit", 100)


sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.moveCamera(False, 300, -600, 178)


sm.sendDelay(1002)


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Bravo, Kaiser. Defiant to the last. I look forward to seeing how many Specters you can cut down before they overwhelm you.")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Sorry to disappoint, but you're going first, Magnus.")


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(0)


OBJECT_4 = sm.sendNpcController(3000128, -750, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
sm.sendDelay(210)


OBJECT_5 = sm.sendNpcController(3000128, -650, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
sm.sendDelay(210)


OBJECT_6 = sm.sendNpcController(3000128, -550, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_6, "summon", 0)
sm.sendDelay(210)


OBJECT_7 = sm.sendNpcController(3000128, -1150, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_7, "summon", 0)
sm.sendDelay(210)


OBJECT_8 = sm.sendNpcController(3000128, -1250, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_8, "summon", 0)
sm.sendDelay(210)


OBJECT_9 = sm.sendNpcController(3000128, -1350, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_9, "summon", 0)
sm.sendDelay(210)


sm.moveCamera(False, 450, -600, 178)


sm.sendDelay(666)


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Oh, I doubt that very much. There are still plenty of Specters here to head you off. That poison taking effect yet?")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("You're a coward, Magnus. Hiding behind your minions and dirty tricks, all earned by licking the boots of Darmoor. You have no honor.")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Honor is overrated. I just want to watch you suffer, and look! I got what I wanted.")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Oh, but don't worry. I'm merciful enough to end your life with my own hands. Kaiser may return, but I'll take great pleasure in ending your career.")


sm.sendDelay(2000)
sm.avatarOriented("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon0")
sm.reservedEffect("Effect/Direction9.img/kaiserTutorial/Scene2")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("(This poison is spreading too fast. This might be my last chance...I have to do what I can to end this quickly.)")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Even when you reincarnate, you'll be right back to square one. Too weak to oppose us. It's all over for you.")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Enough talk, Magnus. Let us end this.")


sm.showEffect("Effect/Direction9.img/effect/tuto/Effect/0", 0, 0, 0, -2, -2, False, 0)
sm.sendDelay(1200)


sm.hideUser(True)
OBJECT_10 = sm.sendNpcController(3000142, -900, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_10, "summon", 0)
sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Now THIS is what I wanted! I was afraid the poison might stop you from transforming, but you don't disappoint. I always wanted to test my strength against your true form. Have at you!")


sm.showFieldEffect("demonSlayer/whiteOut", 0)
sm.sendDelay(5000)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_4, False)
sm.sendNpcController(OBJECT_5, False)
sm.sendNpcController(OBJECT_6, False)
sm.sendNpcController(OBJECT_7, False)
sm.sendNpcController(OBJECT_8, False)
sm.sendNpcController(OBJECT_9, False)
sm.sendNpcController(OBJECT_10, False)
# [FORCED_STAT_RESET] []
sm.warp(940002030, 0)
