# [Commerci Republic] The Problem with Presumptions

MAYOR_BERRY = 9390201

sm.setPlayerAsSpeaker()
sm.sendNext("Excuse me, Mayor? A-are you busy?")

sm.setSpeakerID(MAYOR_BERRY)
sm.sendNext("You stopped by at just the right time, kiddo. "
            "It'd been a fishful day, and that's the best kinda day, if you ask me.")

sm.setPlayerAsSpeaker()
sm.sendNext("Oh, great! That's... I need to tell you something.")

sm.setSpeakerID(MAYOR_BERRY)
selection = sm.sendNext("Well, go on and spit it out!\r\n"
            "\r\n"
            "#L0##b(I should rethink this.)#l\r\n"
            "#L1##b(I've got to tell him the truth.)#l")


sm.setPlayerAsSpeaker()
if selection == 0:
    sm.sendNext("Oh, I just wanted to tell you what a beautiful day it is")
    sm.setSpeakerID(MAYOR_BERRY)
    sm.sendNext("Oh, ye. It truly is a gorgeous day, today")
elif selection == 1:
    sm.sendNext("The truth is... I lied to you. The sea didn't bring me here...")

    sm.setSpeakerID(MAYOR_BERRY)
    sm.sendNext("Well, unless you've got a set of wings curled up somewhere, "
                "how'd you get here? And what do you mean you lied?")

    sm.setPlayerAsSpeaker()
    sm.sendNext("I came across the northern barrier.")

    sm.setSpeakerID(MAYOR_BERRY)
    sm.sendNext("What kinda hogwash is that? Nobody's been across the northern barrier in a hound's age. "
                "Ain't nothin' but demons and evil up there anyway. "
                "You sayin' you're some kinda demon?!")

    sm.setPlayerAsSpeaker()
    sm.sendNext("No, no... the demons are... Look, I'm from a place called Maple World, and I guess technically you are too. "
                "I traveled to Dawnveil from a different continent, far away.")

    sm.setSpeakerID(MAYOR_BERRY)
    sm.sendNext("Well that's just plum silly. Another continent? "
                "I was sure this sea just went on forever... "
                "If this is all true, how'd you get across that barrier?")

    sm.setPlayerAsSpeaker()
    sm.sendNext("The barrier is breaking down... Where I came from, no one had even heard of Dawnveil, "
                "and our leaders feared it might be a danger to the rest of Maple World.")

    sm.sendNext("I was sent here to explore, investigate and to see if our two peoples could have a peace.")

    sm.setSpeakerID(MAYOR_BERRY)
    sm.sendNext("Well, I'll be a toad on a hot plate. I don't much care for bein' on the other end of a lie, "
                "but I can tell you wasn't tryin' to cause no trouble.")

    sm.setPlayerAsSpeaker()
    sm.sendNext("Thanks... I'm really sorry.")

    sm.setSpeakerID(MAYOR_BERRY)
    sm.sendNext("Let's let bygones go on down bygone way. Can I help you with your plan?")

    sm.setPlayerAsSpeaker()
    sm.sendNext("Maybe you can... Your people really seem to be about the same as ours, "
                "but I think the fear of what's beyond the barrier could stir up trouble. "
                "They might perceive me as a threat...")

    sm.setSpeakerID(MAYOR_BERRY)
    sm.sendNext("Well, people ain't always kind to the ones that come from afar, "
                "and changing minds one by one will get you nowhere fast. I've got me another plan.")

    sm.setPlayerAsSpeaker()
    sm.sendNext("Yes?")

    sm.setSpeakerID(MAYOR_BERRY)
    sm.sendNext("Go on down to the docks. "
                "There's a ship there that belongs to the Prime Minister of #bSan Commerci#k, a fella named #e#bGilberto Daniella#k#n. "
                "His boy, #e#bLeon#k#n, is the captain of the ship. "
                "They're good folk, and they hold a lot of sway.")

    sm.sendNext("You show those Daniellas you're worth trustin', and you might just have yourself a strong supporter in the richest nation of Dawnveil.")

    sm.setPlayerAsSpeaker()
    sm.sendNext("You're a lot smarter than I took you for. I'm sorry for underestimating you. "
                "I don't know how I can thank you...")

    sm.setSpeakerID(MAYOR_BERRY)
    sm.sendNext("You've done right by me and mine. You just do what's best for all our people, "
                "and I'll call that thanks enough. Now go on, get, before I have to get my broom and shoo you off! "
                "Find #e#bLeon Daniella#k#n in the guest house on the east end of town.")

    sm.sendNext("And take this! It ain't much, but think of it as a souvenir!")

    sm.sendNext("Don't get shy, just take it!")

    sm.startQuest(parentID)
    sm.giveItem(1003984) # Commerci Hat
    sm.completeQuest(parentID)
    sm.dispose()
