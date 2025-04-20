LIBRARIAN = 2500002

sm.setSpeakerID(LIBRARIAN)
sm.sendNext("Did you think of a keyword? What word do you want to search?\r\n\r\n#L0##bBlack Mage#l")
sm.sendNext("Black Mage, huh? That's a pretty big topic. I'm seeing titles like The Sealing of the Black Mage, Commanders of the Black Mage, Appearance of the Black Mage... 3849 results.")
sm.sendNext("You need to narrow down your search. What do you want to search for?\r\n\r\n#L0##bThe Black Mage's Resurrection#l")
sm.sendNext("The Black Mage's Resurrection... Interesting, that's just one book. But uh... There's a problem.")
sm.sendNext("This book isn't available to the public. I'm sorry, but I can't help you with this one. Do you happent o have another search in mind?\r\n\r\n#L0##bSeal of Time#l")
sm.sendNext("Seal of Time... There's a book about the Seal of Time that locked up the Black Mage. Let's see, let's see... Ah, so that's what happened...")
sm.sendNext("There's a book called #rThe Black Mage and the Five Heroes#k that's probably everything you're looking for. You want to search for anything else?\r\n\r\n#L0#(#bYou whisper something#k.)#l")
sm.sendNext("Really? Is that your name? I love doing name searches for people! Let's see what your life is like...")

sm.setPlayerAsSpeaker()
sm.sendSay("No, it's fine. Don't search my name.")

sm.setSpeakerID(LIBRARIAN)
sm.sendSay("Why? You got a lot of secrets or something? Ah well, every one has secrets, I suppose. Very well.")

sm.setPlayerAsSpeaker()
sm.sendSay("(Maybe there are records of me here, if the library truly has ALL the records of the world. I have to keep pressing forward.)")
sm.sendSay("(I won't dwell on the past, even if it means more pain!)")

sm.completeQuest(parentID)
sm.jobAdvance(2512)
