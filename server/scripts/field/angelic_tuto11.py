# Created by MechAviv
# Map ID :: 940011110
# Eastern Region of Pantheon : East Sanctum

# [SET_DRESS_CHANGED] [01 00 ]
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(2)
sm.sendDelay(60)


sm.forcedInput(0)
sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg1/2", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Holy moly, you look amazing! Look at those legs! And that hair! And your-- why are you looking at me like that?")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("W-what am I wearing?! Where are my pants?!")
# Unhandled User Effect [PlaySoundWithMuteBGM] Packet: 23 15 00 56 6F 69 63 65 2E 69 6D 67 2F 41 6E 67 65 6C 69 63 5F 46 2F 31


OBJECT_1 = sm.sendNpcController(3000119, 150, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendDelay(90)


OBJECT_2 = sm.sendNpcController(3000115, 250, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.sendDelay(90)


OBJECT_3 = sm.sendNpcController(3000111, 350, 220)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.sendDelay(90)


sm.setSpeakerID(3000119)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("There! That girl has the relic on her arm!")


sm.setSpeakerID(3000115)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Do we really need to hurt a pretty little thing like her? The relic is out of the Sanctum, it won't cause us any trouble.")


sm.setSpeakerID(3000119)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Don't be stupid! We can't let some little bimbo take credit for our actions!")


sm.setSpeakerID(3000111)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I-I'm sorry, um, ma'am, but would you please give me that relic?")


sm.setSpeakerID(3000111)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("You're one of those creeps I saw before!")


sm.setSpeakerID(3000111)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Well, I wouldn't say 'creep' so much as liberator. Look, maybe you can give me that beautiful bracelet of yours for a bit and I can take you out for dinner...")


sm.setSpeakerID(3000111)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("No way, you old weirdo! I can't even get this thing off of my arm.")


sm.setSpeakerID(3000119)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Would you stop flirting?! Just grab the bimbo and we'll get out of here!")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("#h0#! Now would be a good time to kick the tar out of them.")


sm.setSpeakerID(3000132)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("H-how do I do that?!")


sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg1/3", 900, 30, -70, -2, -2, False, 0)
sm.sendDelay(900)


sm.setSpeakerID(3000119)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("What mad devilry is going on inside that lovely little head of yours? Come now! Let's go!")


sm.sendDelay(300)

sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.spawnMob(9300560, 150, 239, False)
sm.spawnMob(9300561, 250, 239, False)
sm.spawnMob(9300562, 350, 239, False)
sm.chatScript("Press the CTRL key to attack.")
sm.systemMessage("Press the CTRL key to attack.")
sm.showFieldEffect("lightning/screenMsg/0", 0)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)

while sm.hasMobsInField():
    sm.waitForMobDeath()
sm.warp(940001050, 0)