# [Commerci Republic] Eye for an Eye

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("Now, what dream can I make come true for you? Remember, anything in the entire world is yours for the asking.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Can you introduce me to Gilberto Daniella?")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("I offer to make your wildest dreams coe true, and that is what you want?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Yup, I really want to meet Gilberto Daniella.")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("I heard you the first time, it's just...")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("What?")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("Well, I thought you'd ask for something difficult, like borrowing my hat.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("That was next on my list.")

sm.setSpeakerID(9390225) # Tepes
response = sm.sendAskYesNo("To get to the Daniella merchant Union Office, head east from this spot, past the town fountain. "
                "It's the white building with golden ornamentation.")

if response:
    sm.setSpeakerID(9390225) # Tepes
    sm.sendNext("I'll let them know you're on your way. Be polite when you talk to Gilberto. "
                "He is quite powerful in Commerci.")
    sm.startQuest(parentID)
sm.dispose()
