# [Commerci Republic] Eye for an Eye

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Hi Gilberto!")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Er, Yes. I am Gilberto Daniella. Do I know you?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("You sure don't! But my name is...")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("I'm sorry, but I'm quite busy. If you'll make an appointment. I can meet with you later.")
sm.completeQuest(parentID)
sm.warp(865090002, 1) # Hidden  Daniella Merchant Union Office
sm.dispose()
