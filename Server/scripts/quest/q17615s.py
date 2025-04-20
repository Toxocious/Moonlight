# [Commerci Republic] The Trade Kingdom

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Excuse me.")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("Yes?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Could you point me toward the Daniella merchant Union Office?")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("I happen to be a Daniella Merchant myself. If you want a job, you can talk to me right here.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("I don't need a job. I'm looking for #e#bGilberto Daniella#k#n.")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("Ha, do you think the Prime Minister has time for you? He's a busy man. Now, off with you.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("#b(Hmm, now what?)")
sm.completeQuestNoRewards(parentID)
sm.dispose()