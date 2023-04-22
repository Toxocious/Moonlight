# [Shaolin Temple] Investigate the Sutra Repository

WISE_CHIEF_PRIEST = 9310053
ZHEUNG_GUAN = 9310046
BOOK_OF_DEMONS = 4034637

sm.removeEscapeButton()
sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("So the Demon B Gone didn't work? Oh well! Now, there's one other way...")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("(Why did the Head Priest send me to #b#p"+ str(ZHEUNG_GUAN) +"##k if he had so little faith in him?! What a waste of time!)")

sm.sendNext("I hope your next idea is better than your last. You better fill me in.")

sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("Long ago, my master, #bMonk Laomo#k, researched demons. "
            "He knew more about them than anyone He was the first to successfully seal the demons at the Sutra Depository.")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Let's ask HIM to help us!")

sm.setSpeakerID(WISE_CHIEF_PRIEST)
sm.setBoxChat()
sm.sendNext("We can't. He left a long time ago to train and has never returned. "
            "But he did write a book about his research before he left, called #v"+ str(BOOK_OF_DEMONS) +"##b#t"+ str(BOOK_OF_DEMONS) +"##k.")

response = sm.sendAskYesNo("Can I entrust you with the task of searching for that book, #h0#?")

if response:
    sm.sendNext("The #v"+ str(BOOK_OF_DEMONS) +"##b#t"+ str(BOOK_OF_DEMONS) +"##k must hold some clue about how to get rid of the demons. "
                "Locate that book as soon as possible. The fate of the entire world is at stake!")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("Hey, #b#p"+ str(WISE_CHIEF_PRIEST) +"##k... Why didn't your teacher impart any of his knowledge on you before he left?")

    sm.setSpeakerID(WISE_CHIEF_PRIEST)
    sm.setBoxChat()
    sm.sendNext("That's exactly what I intend to ask him, should we and I ever meet again.")

    sm.sendNext("I've lived my entire life in #bMonk Laomo's#k shadow, but he didn't teach me even a single one of his lofty secrets.")

    sm.sendNext("But that has nothing to do with any of this. Hurry and find the #b#t"+ str(BOOK_OF_DEMONS) +"##k.")
    sm.startQuest(parentID)