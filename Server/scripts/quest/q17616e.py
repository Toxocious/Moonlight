# [Commerci Republic] Stolem Items

sm.setSpeakerID(9390220) # Maestra Fiametta
sm.sendNext("Yes? What do you want?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("What can you tell me about the items that were stolen from the Daniella Merchant Union?")

sm.setSpeakerID(9390220) # Maestra Fiametta
sm.sendNext("Not much to tell. A few days ago, a Daniella merchant deposited some items. A little while ago, he picked them up.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Are you sure it was the same guy?")

sm.setSpeakerID(9390220) # Maestra Fiametta
sm.sendNext("Are you sure you have a brain in your skull? Yes, it was the same guy.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("No need to bite my head off.")
sm.completeQuest(parentID)
sm.dispose()