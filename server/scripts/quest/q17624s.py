# [Commerci Republic] Fish out of Water

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Sigh..")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("#b(They seem worried.)#k\r\n"
            "Morning Leon. Good morning Gilberto.")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Oh, #h0#, my sidekick. You're awake.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("#b(How should I approach this...)#k\r\n"
            "Did you sleep well Leon? Is something the matter? Gilberto, you look worried as well.")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("The Commerci Republic is known for it's commerce. The sea trade is great, but the land trade is weak.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Huh?")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Sigh, allow me. Leon still has to work on this speaking skills.")

sm.sendNext("The #bDelfinos#k are responsible! They prey on our good merchants from the San Commerci canals!")

sm.sendNext("It has forced us to isolate ourselves. "
            "It's costing me more than a pretty penny, let me tell you.")

sm.sendNext("#b(Delfino? I don't think they're in Maple World, but either way they seem to be causing trouble. "
            "This might be my chance to earn Gilberto's trust, so I should inquire")
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.dispose()
