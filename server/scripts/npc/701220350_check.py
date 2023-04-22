# Bookshelf (9310600)  |  Hidden Street : Secret Library

BOOK_OF_DEMONS = 4034637
WISE_CHIEF_PRIEST = 9310053
GOBLIN_DEMON = 9310578
BLUE_DEMON = 9310577
NINE_TAILED_FOX = 9310579

if not sm.hasItem(BOOK_OF_DEMONS):
    sm.removeEscapeButton()
    sm.setBoxChat()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    if not sm.canHold(BOOK_OF_DEMONS):
        sm.sendNext("Let's make some space in my inventory.. I think I see the book right there.")
        sm.dispose()
    sm.sendNext("Huh? This is the #rBook of Demons#k")

    sm.giveItem(BOOK_OF_DEMONS)
    sm.warpInstanceIn(701220350) # hidden library

    sm.lockInGameUI(True, False)

    sm.sendNext("Eureka! This must be the book the #p"+ str(WISE_CHIEF_PRIEST) +"# needs. I'd better go give it to him.")

    sm.setSpeakerID(NINE_TAILED_FOX)
    sm.setBoxChat()
    sm.sendNext("Wait, #b#h0##k! I have a gift for you!")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("Why are you holding a mirror? Is that for me?")

    sm.setSpeakerID(NINE_TAILED_FOX)
    sm.setBoxChat()
    sm.sendNext("It's no ordinary mirror.\r\n"
                "It shows a person's true form, human or demon.\r\n"
                "Perhaps you'll find it useful someday.")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("That's amazing! Thanks! Now I better run and deliver this book to the #p"+ str(WISE_CHIEF_PRIEST) +"#.")

    sm.warpInstanceOut(701220300, 2) # Sutra 5-6
    sm.lockInGameUI(False)