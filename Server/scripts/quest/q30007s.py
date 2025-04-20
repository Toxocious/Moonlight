# [Root Abyss] World Tree In Danger

MYSTERIOUS_GIRL = 1064001 # npc Id
sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("What happened? You disappeared all of the sudden... I was worried.")

sm.setPlayerAsSpeaker()
sm.sendNext("They took me away.")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("Who? The same people that trapped me here?")

sm.setPlayerAsSpeaker()
sm.sendNext("I think so, yeah. There was this really mean guy that kept threatening me.")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("Are you scared? I thought you were going to help me!")

sm.setPlayerAsSpeaker()
sm.sendNext("I'm not scared! I'm just... concerned... about getting beat up. "
            "Look, I'll be honest, I need some back up.")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("What should we do? I can't help with all this dark energy sapping my powers.")

sm.setPlayerAsSpeaker()
sm.sendNext("The Maple Alliance can help.")

sm.sendNext("I'll head to Ereve right away. If anybody can figure out how to unseal Root Abyss. "
            "It's Neinheart. He might even be mildly amused to see me alive.")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("Okay... but come back fast, okay? I can feel the darkness draining my life away... "
            "I don't know how long I have left.")

sm.sendNext("I'll be back before you know it. Just stay strong.")
sm.startQuest(parentID)
sm.warp(130000000, 0) #Ereve
sm.dispose()
