# [Stone Colossus] Colossal Clean Up 4

UNKNOWN_PERSON = 2210009 #     Npc Name = ???

sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("I can't believe the Goddess would create such a vile and vicious monster.")

sm.setSpeakerID(UNKNOWN_PERSON)
sm.flipDialogue()
sm.sendSay("This is only the beginning. There is so much more in store...")

sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("#b(The stranger's voice fades with the passing wind.)")

sm.warpInstanceOut(240092000, 0) # Top of Chino's Station
sm.completeQuest(parentID)

sm.removeEscapeButton()
sm.startQuest(31349) #  [Stone Colossus] Colossal Clean Up 5
sm.sendSayOkay("I should check on the Stone Colossus!")