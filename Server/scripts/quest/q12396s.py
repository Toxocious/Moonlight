# Character potential unlock quest. Used to be only for the 3rd line, got changed to be the initial quest.
pm = sm.getChr().getPotentialMan()
sm.addCharacterPotentials()
sm.sendSayOkay("Character potential unlocked.")
sm.completeQuestNoCheck(12396)
