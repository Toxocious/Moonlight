# [Shaolin Temple] Investigate the Sutra Repository

BOOK_OF_DEMONS = 4034637
WISE_CHIEF_PRIEST = 9310053

sm.removeEscapeButton()
sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("Oh! The #bBook of Demons#k! You didn't... You didn't open the book, did you?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("I didn't have time. I was too busy rushing back here so we can save the world!")

sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("Wonderful. You did beautifully. Now, off with you. Rest Up. You've earned it. I have things do.")

sm.completeQuest(parentID)
sm.giveExp(3030679)
sm.consumeItem(BOOK_OF_DEMONS)
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("(Whoa. the #b#p"+ str(WISE_CHIEF_PRIEST) +"##k rushed out of here. That book must be filled with really great info. His eyes lit up when he opened it.")

sm.sendNext("(Well, another job well done. Time to say goodbye to my favourite demon trio back at the #rSutra Depository Secret Library#k.)")
sm.chatScript("Say farewell to the demons at the Sutra Depository Secret Library")