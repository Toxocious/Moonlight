# Created by MechAviv
# Map ID :: 940001110
# Heliseum : Heliseum Outskirts

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
OBJECT_1 = sm.sendNpcController(3000131, -390, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.moveCamera(False, 300, -600, 170)


sm.sendDelay(999)

sm.reservedEffect("Effect/Direction9.img/kaiserTutorial/Scene2")
sm.sendDelay(1000)


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("How DARE you lay Heliseum at Darmoor's feet! You are a DISGRACE to the people of Nova!")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("The Council spared you, and this is how you repay them? I'll never forgive you! NEVER!")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I wouldn't expect you to understand. I want power...and Darmoor gave it to me.")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I don't know what kind of power you've got, and I don't care. This wound isn't going to stop me from striking you down!")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Temper, temper. I don't think you understand your situation. Let me break it down for you.")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("First, I admit that I might not have the power to defeat you, mighty Kaiser, even with my added power from Darmoor. However, don't make the mistake of thinking I have no plan to counter your strength.")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("You say your wound isn't going to stop you. But that blade was coated in a vicious poison that will sap your strength, tipping the odds in my favor.")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Heh... Then all I have to do is beat your before the poison takes full effect.")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Indeed. Which brings me to my second point. You're not just fighting me, you know. Heliseum has been overrun with thousands of Specters, all under my command. Even at full strength, I doubt you could beat so many.")


sm.setSpeakerID(3000131)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I won't know until I try.")


OBJECT_2 = sm.sendNpcController(3000125, -750, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.sendDelay(210)


# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 32 00 00 00 FF 00 00 00 00
OBJECT_3 = sm.sendNpcController(3000122, -650, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.sendDelay(210)


OBJECT_4 = sm.sendNpcController(3000125, -550, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
sm.sendDelay(210)


sm.moveCamera(False, 450, -1300, 170)


sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(1526)


OBJECT_5 = sm.sendNpcController(3000122, -1150, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
sm.sendDelay(210)


OBJECT_6 = sm.sendNpcController(3000125, -1250, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_6, "summon", 0)
sm.sendDelay(210)


OBJECT_7 = sm.sendNpcController(3000122, -1350, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_7, "summon", 0)
sm.sendDelay(210)


OBJECT_8 = sm.sendNpcController(3000125, -1450, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_8, "summon", 0)
sm.sendDelay(210)


OBJECT_9 = sm.sendNpcController(3000122, -1550, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_9, "summon", 0)
sm.sendDelay(210)


OBJECT_10 = sm.sendNpcController(3000125, -1650, 170)
sm.showNpcSpecialActionByObjectId(OBJECT_10, "summon", 0)
sm.moveCamera(True, 0, 0, 0)


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(30)


sm.sendDelay(2000)
sm.avatarOriented("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon0")
sm.reservedEffect("Effect/Direction9.img/kaiserTutorial/Scene2")


sm.showEffect("Effect/Direction9.img/effect/tuto/BalloonMsg2/0", 0, 0, -120, -2, -2, False, 0)
sm.sendDelay(2000)


sm.moveCamera(False, 450, -600, 170)


sm.sendDelay(585)


sm.showNpcSpecialActionByObjectId(OBJECT_1, "alert", 0)
sm.showEffect("Effect/Direction9.img/effect/tuto/BalloonMsg1/2", 0, 0, -130, 0, OBJECT_1, False, 0)
sm.sendDelay(2000)


sm.moveCamera(True, 0, 0, 0)


sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.sendNpcController(OBJECT_4, False)
sm.sendNpcController(OBJECT_5, False)
sm.sendNpcController(OBJECT_6, False)
sm.sendNpcController(OBJECT_7, False)
sm.sendNpcController(OBJECT_8, False)
sm.sendNpcController(OBJECT_9, False)
sm.sendNpcController(OBJECT_10, False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.showFieldEffect("lightning/screenMsg/0", 0)
sm.sendNpcController(OBJECT_1, False)
# [FORCED_STAT_RESET] []
sm.warp(940001150, 0)
