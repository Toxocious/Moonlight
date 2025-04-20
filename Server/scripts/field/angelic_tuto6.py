# Created by MechAviv
# Map ID :: 940011060
# Pantheon : Pantheon Clinic

# [SET_DRESS_CHANGED] [00 00 ]
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(1)
sm.sendDelay(60)


sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(3000106, 160, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000107, 10, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(3000152, 90, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.setSpeakerID(3000152)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("#h0#, you have finally come to.")


sm.setSpeakerID(3000152)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Umm, where am I?")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Pantheon. How do you feel?")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("My head feels like a ripe watermelon, but I think I'm okay otherwise.")


sm.showEffect("Effect/Direction10.img/effect/story/BalloonMsg1/0", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(600)


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Huh? Why is there a pink thing on my arm?")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I wish I had better news, child, but I fear you have been cursed by the East Sanctum relic. In fact, it is quite stuck to your arm.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("What?! What do I do?! How do I get it off?!")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("The security threat of having a young, defenseless girl wandering around with one of our relics strapped to her arm has not escaped me.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Do not scold the girl, Cartalion. That relic would be entirely gone if it were not for Kaiser and #h0#.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I suppose you are right, as usual.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Ha... hahaha... what? I don't remember anything...")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Are you saying that the relic grabbed me and turned into a bracelet when I touched it? Who the heck is Kaiser? What is going on?!")


sm.setSpeakerID(3000152)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Hey, it's going to be okay. We don't have a way to remove that relic from your arm, but it won't cause you any harm. Think of it like a nice accessory.")


sm.setSpeakerID(3000152)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I-I didn't mean to take it! I don't even like pink!")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("#h0#, no one is blaming you for this. Three relics remain in Pantheon. We are quite safe.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("B-but, I...")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("#h0#, please don't start crying. I'm a very sensitive sympathy-weeper.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Ugh...")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Cartalion!")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'm sorry. I have very little control over my tearducts.")


sm.forcedInput(1)
