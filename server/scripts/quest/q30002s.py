# [Root Abyss] The Mysterious Girl

MYSTERIOUS_GIRL = 1064001 # npc Id

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("I want to get out of here.")

sm.setPlayerAsSpeaker()
sm.sendNext("What?")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("I said I want to get out of here.")

sm.setPlayerAsSpeaker()
sm.sendNext("Oh, well I need to stay. I'm supposed to find out more about this place before I go.")

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("This place? This is Root Abyss. And you don't want to be here. "
            "Let's leave together. Follow me")

sm.setPlayerAsSpeaker()
sm.sendNext("Wha- Where are you going?!")

response = sm.sendAskYesNo("#b(She looks lost... maybe I should help her out?)")

if response:
    sm.sendNext("All right, fine. I'll show you how to get out.")
    sm.startQuest(parentID)
else:
    sm.dispose()

sm.setSpeakerID(MYSTERIOUS_GIRL)
sm.sendNext("You are really going to help, right! You promised!")
sm.dispose()
