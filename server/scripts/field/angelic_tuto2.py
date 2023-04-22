# Created by MechAviv
# Map ID :: 940011020
# Eastern Region of Pantheon : Near East Sanctum

# [SET_DRESS_CHANGED] [00 00 ]
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(3000140, -1400, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000104, -1650, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("It's so pretty out today! I wanna take a nap!")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You're such a lazy bum, #h0#. Kyle and I manage to become knights already, and here you are trying to sleep more!")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Hey, I'm not a fighter like you guys! Unless I magically sprout a set of super powers, I'm gonna lounge around alllll day every day.")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'm pretty sure you've told me that one about a thousand times.")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Oh, I'm sorry, am I boring you? Should I be congratulating you two on your fancy new titles? I'll join you one day!")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I don't think you really need to be a knight, #h0#. ")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("What are you talking about? We're the Heliseum Force! We have to fight!")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Yeah, but you don't use magic. You have to face the truth sometime...")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Ugh, not everybody HAS to use magic, ya know? You're so thickheaded sometimes...")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I just want you to think sometimes. Anyway, I gotta get back.")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Oh, I wish I could go...")


sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/0", 1200, 0, -120, 0, OBJECT_1, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("What was that?")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("What are you talking about? Come on, you can daydream about smooching with #h0# on the way back to camp.")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("No, something's wrong! on! We need to get to the East Sanctum!")


sm.setSpeakerID(3000140)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Let's get moving! Heliseum Force, go!")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Seriously? How in the world would you know what's going on at the East Sanctum?")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("C'mon Veldie! Kyle's gut is hardly ever wrong. Besides, I'm bored!")


sm.setSpeakerID(3000104)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Why did you guys even make me captain if we're always going to follow Kyle's stupid gut?")


sm.moveNpcByObjectId(OBJECT_1, False, 400, 100)
sm.moveNpcByObjectId(OBJECT_2, False, 400, 100)
sm.showEffect("Effect/Direction10.img/effect/tuto/BalloonMsg0/0", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(600)


sm.forcedInput(2)