# [Root Abyss] Root Ruckus 1

MYSTERIOUS_GIRL = 1064001 # npc Id
sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("Did you find a way out?")

sm.setPlayerAsSpeaker()
sm.sendNext("There's an exit not too far from here.")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("I've already tried that about a hundred times, but I can't get out.")

sm.setPlayerAsSpeaker()
sm.startQuest(parentID)
sm.sendNext("Uh... well it worked for me. Let me go check it out.")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("Please be quick. I really don't like it here...")
sm.dispose()