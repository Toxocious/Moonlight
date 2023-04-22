# [Commerci Republic] Gilberto Daniella

sm.startQuest(parentID)
sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Time is money, and my time is worth a million mesos a minute. Now, please make an appointment.")

sm.setSpeakerID(9390225) # Tepes
sm.spawnNpc(9390225, -104, 75) # Spawn Tepes
sm.sendNext("Actually, sir, this young Explorer got our stolen goods back")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Excuse me? Our goods were stolen?")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("Er, you see, sir, these cutthroat bandits stole the goods right out of my hands. "
            "I fought back and even fore my pants, but it was this young Explorer who saved me. "
            "I'll... be on my way now.")
sm.removeNpc(9390225) # Remove Tepes

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("#b(Tepes is a pretty convincing liar.)")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Ah, well, in that case, I thank you for your help, young Explorer.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Aw, shucks. Tepes is exaggerating.. a lot.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("It is a pleasure to meet you. I am #e#bGilberto Daniella#k#n, "
            "prime minister of the Commerci Republic and owner of the Daniella Merchant Union")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("The pleasure is mine. I'm #h0#. I'm, um, just a traveler, "
            "traveling through Dawnveil. You know, just traveling.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Ah, to be young and free again! But why did you want to see me?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("um... Well... #b(Okay, I'm going to have to word this carefully, so he doesn't think I'm a devil.)")

sm.spawnNpc(9390256, 21, 75) # Spawn Leon Daniella
sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Father! I'm hooooooome!")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("How many times do I need to remind you to call me 'prime minister' in public, Leon?")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Sorry pops, I mean prime minister.")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Hey #h0#, you're here as promised! Great!")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("You two know each other? This young traveler retrieved some stolen goods for us... "
                "Or defeated some bandits? I'm still not clear on the story.")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Way to go, #h0#, buddy! First you saved me in Berry, like the excellent sidekick you are, "
            "and now you're impressing my daddy!")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("You exhibit magnificent skills for a mere traveler, #h0#. The union is in your debt.")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("A mere traveler? but #h0# is from beyond the barrier.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("#h0# is... what?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("(Yikes!) Er, yeah, I TRAVELED here from a place called Maple World, which just so happens to be beyond the barrier...")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Just a moment ago, you clearly said you were a DAWNVEIL traveler...")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Here's the truth, prime minister. The barrier between our worlds is growing weaker by the day, which made is possible for me to pass through it.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("And why are you here?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Like Leon said, I'm an ambassador of peace, here on behalf of Empress Cygnus.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("An embassador of peace, you say?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Yes. When the barrier inetivably collapses, I hope the people of Commerci and Maple World can get along amicably")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("I see. Well then, you've traveled a long way. Please make yourself at home and rest. The Union is still in your debt.")
sm.removeNpc(9390256) # Remove Leon Daniella
sm.completeQuest(parentID)
sm.warp(865000002, 0)
sm.dispose()
