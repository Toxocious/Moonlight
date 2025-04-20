# [Commerci Republic] Come Back Here!

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("I can't believe it. All those items... stolen! And the iron I ordered was in there too")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Hey Tepes!")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("And the white carnation buttons I was going to sew on my vest were in there too... I can't believe they're all gone.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("#b(Aww, he looks seriously depressed.)")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Hellooooo I got your items back. See?")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("And the silver ribbons I ordered to tie my-- Wait, what? You got my items! Oh, happy day! How'd you do it?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("It was easy. I tracked down the thief.")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("Incredible. That'll teach me to judge people based on their clothing.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("#b(Hey! What did he mean by that?!)")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("Anyway. I owe you one, my friend. Anything you need, I'll make it happen. I swear it.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Are you serious?")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("On my very life. Whatever you need.")
sm.completeQuest(parentID)
sm.dispose()
