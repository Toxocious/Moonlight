# [Root Abyss] World Tree In Danger

NEINHEART = 1404009
sm.setSpeakerID(NEINHEART)
sm.sendNext("You're alive! I find that slightly amusing. Did you investigate the area we spoke of?")

sm.setPlayerAsSpeaker()
sm.sendNext("Oh man, let me tell you...")

sm.sendNext("#b(You tell Neinheart what happened in Root Abyss)#k")

sm.setSpeakerID(NEINHEART)
sm.sendNext("If what you say is not an injury-induced hallucination, this certainly sounds like a problem."
            "I am excited to hear that the World Tree has finally been located, but it seems to be in a rather precarious situation.")

sm.setPlayerAsSpeaker()
sm.sendNext("The World Tree is stuck in Root Abyss, and she's not going to last long with all the dark energy.")

sm.setSpeakerID(NEINHEART)
sm.sendNext("I had that figured out already, thank you very much. "
            "I need a moment to think. Leave me")
sm.completeQuest(parentID)
sm.dispose()
