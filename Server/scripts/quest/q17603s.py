# [Commerci Republic] Parbell, World's 'Greatest' Explorer

sm.setSpeakerID(9390200) # Parbell
sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Excuse me, are you Parbell the Explorer?")

sm.setSpeakerID(9390200) # Parbell
sm.sendNext("What d'yuh means, don't yuh recognize me? Gaze 'pon this hansom visage! "
            "Haven't ya heard about ol'Parbell, the Greatest 'Splorer in the whole o' Maple World!? "
            "Sheesh, young'uns these days!")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("(He seems a little upset.)")

sm.setSpeakerID(9390200) # Parbell
sm.sendNext("So, I hears yuhs seeking' passage to the good ol' 'Public o' Commerci...\r\nThat about right?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Y-Yessir, I's am... I mean, that's right. Why do you ask?")

sm.setSpeakerID(9390200) # Parbell
sm.sendNext("Hows yuh plannin' to get there?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("E-Excuse me...? I was... Neinheart, said you would have a ship ready for me, uh, Mr. Great Explorer, sir...")

sm.setSpeakerID(9390200) # Parbell
sm.sendNext("Oh, Parbell the Great, done readied a ship for you. There's no doubtin' that. "
            "Question is, how yuhs plan on getting on that ship?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("I... uh.. By walking? With my feet?")

sm.setSpeakerID(9390200) # Parbell
sm.sendNext("Y'uns about as smart as a bag of hammers, ain't yuhs? "
            "Let ol' Parbell make it all simple-like for y'un. I gots a ship. "
            "You wants to get on that ship. I done readied mah ship like I's told. "
            "#eTraditionally at this point some form o' currency get s'changed#n.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("(Are you kidding me? Neinheart expects me to pay for the trip myself? He's in for a stern talking-to...)")

sm.sendNext("Y-You know I've been sent by the Empress, right?")

sm.setSpeakerID(9390200) # Parbell
sm.sendNext("Empress? She ain't MAH Empress. Shucks, this here's why I never deal with no greenhorns... "
            "You gots you way with words, I gives yuh that. "
            "Well, get on mah ship, a promise is a promise. "
            "I'll deliver you to Commerci all safe 'n cozy-like, but remember yer offer. "
            "Words carry weight.")

response = sm.sendAskYesNo("I'll be collectin' on that promise, 'fore long. "
            "When all's said 'n done, y'uns shold feel plum tickled that I, Parbell the Great, am showin' yuhs the way! "
            "\r\nReady to set sail?\r\n"
            "#b(You will be moved to Commerci if you accept.)")

if response:
    sm.warp(865010200, 0)
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.startQuest(17608) # [Commerci Republic] After a Pleasant Voyage
else:
    sm.sendSayOkay("Dun't yer let a ol' man waiting!")
sm.dispose()
