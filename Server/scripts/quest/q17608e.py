# [Commerci Republic] After a Pleasant Voyage

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Excuse me... Hi. Could I ask you a question?")

sm.setSpeakerID(9390201) # Mayor Berry
sm.sendNext("Dear me, you look about as healthy as a gutted guppy! Oh I don't mean no insult. "
            "You run into a bit of weather out there? "
            "You should thank your lucky stars you landed here in one piece!")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("I would like to know where I landed, where am I?")

sm.setSpeakerID(9390201) # Mayor Berry
sm.sendNext("Oh, you landed in the Commerci Republic, a gorgeous place!")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("I landed in the Commerci Republic? This is the Commerci Republic?")

sm.setSpeakerID(9390201) # Mayor Berry
sm.sendNext("Yes, that's correct. You landed in the Commerci Republic. As a matter of fact, I'm the mayor of the Commerci Republic.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("#b(This little fishing village is the Commerci Republic? I guess there's no truth to the rumors.)")

sm.setSpeakerID(9390201) # Mayor Berry
sm.sendNext("You seem quite strong, having survived that ship wreckage. "
            "Once you've gotten your energy back, you reckon you could help me out with some things? "
            "I would certainly appreciate it")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Really? But we've just met.. you're going to trust me just like that?")

sm.setSpeakerID(9390201) # Mayor Berry
sm.sendNext("With age comes wisdom, youngster. I can tell you's a good person just by usin' these old peepers. Now enjoy yourself for now! "
            "Let me know if there's anything you need.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("#b(...He seems nice enough, This person claims to be the Mayor, "
            "so I guess I could deliver the Empress's message to him. "
            "I'll wait to make sure he really trusts me before I bring up the peace treaty.")
sm.completeQuest(parentID)
sm.dispose()
