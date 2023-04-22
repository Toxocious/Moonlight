# Created by MechAviv
# Map ID :: 940001240
# Pantheon : Pantheon Clinic

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
sm.forcedInput(1)
sm.sendDelay(60)


sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(3000106, 150, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000107, 70, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Are you awake?")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Fenelle? Urgh... Where am I? Am I still alive?")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You're fine now.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Cartalion? Oh, man. And Tear! What happened to Tear?")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("She is safe, though not quite the same...")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Wait...why are you guys looking at me funny? Do I have something on my face?")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You are Kaiser now. We have awaited Kaiser's return for so long.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Kaiser? Me? That makes no sense.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Velderoth told us about the attack on the East Sanctum. When Fenelle and I arrived, we found you in your Kaiser form.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You had defeated the Nefarious Priests yourself, and lost consciousness.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Don't worry about Tear. She's fine, though... She's been cursed by the Relic.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Cursed? Is that better or worse than me being Kaiser? Because I'm having trouble believing any of this.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("This is no joke. Your aura and the transformation sigil you left make it clear you are Kaiser. You are the chosen one.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("All right, then. What do I do now?")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Accept your destiny. Act with courage and compassion.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("It will be a tough path for a young person like you, but that is the price of great power.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Hey, I'm all about great power. The greater, the better. However, I don't really feel all that different.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Kaiser is a being that reincarnates, but loses all previous abilities and memories. You are like a rough gemstone with unlimited potential.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You must train hard to live up to the previous Kaiser. This is your burden now.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Don't push the young one too hard, Cartalion. There is plenty of time to grow.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("It may be best for you to hide your awakening for now, Kaiser. Our enemies may try to take advantage of your inexperience.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I'll fight anyone who comes looking for trouble. But I'm wondering about Tear. Where is she?")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("She woke up before you did and ran off. She's somewhat distressed about her condition. Kylan went after her, so don't worry yourself too much.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("My brain feels all fuzzy. I feel plenty strong, but I need to get everything straight.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Don't force yourself.")


sm.forcedInput(1)
