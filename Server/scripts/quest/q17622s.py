# [Commerci Republic] Gilberto's Reaction

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Did your dad look upset to you? I don't think he trusts me...")
sm.startQuest(parentID)

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Nah, he was smiling beneath his mustache, I promise. That's how he always looks at me, too. "
            "So, how long are you staying in town for?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("A while, it seems. I have a feeling this won't be easy.\r\n"
            "#b(No thanks to you, Leon.)")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Check out the union while you're around. That's what a good sidekick would do! Now, go rest up.")
sm.completeQuest(parentID)
sm.warp(865000002, 2) # Warp player in the same map, but to a different portal
sm.scriptChat("Whilst sleeping, you overheard Leon and Gilberto talking about you, it seems that Gilberto does not trust you yet.")
sm.startQuest(17623) # [Commerci Republic] Another Outsider
sm.completeQuest(17623) # [Commerci Republic] Another Outsider
sm.dispose()
