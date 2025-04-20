# [Commerci Republic] Delfino Deleter 4

sm.setSpeakerID(9390256) # Leon Daniella
response = sm.sendAskYesNo("I'll be honest.. These fish were definitely a lot tougher! It's what I was born to do!")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("At least we made it. Now let's get out of here before the really tough fish come out.")

sm.completeQuest(parentID)
sm.dispose()