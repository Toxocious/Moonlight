# [Commerci Republic] Stolen Items

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("What to do, what to do? They'll blame me for sure!")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Huh? What's wrong?")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("You again? I don't have time right now. The items I left at the Trading Post have dissapeared!")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("They were stolen?")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("Yes, and do you know who the prime suspect is?.. Me! They say they saw someone in the same clothing as me pick them up..")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Wait, your items were stolen... and you're the main suspect? How does that make sense?")

sm.setSpeakerID(9390225) # Tepes
sm.sendNext("Someone must've copied my fabulous fashion sense and then taken the goods, posing as me. "
            "But who's going to believe that? I'm going to lose my job, and I can't give up my sailor's wardrobe, I just can't.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
response = sm.sendAskYesNo("#b(This seems like my chance to get in good with the Daniella merchant Union!)")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
if response:
    sm.sendNext("#b(I'll head to the Trading Post to investigate further.)")
    sm.startQuest(parentID)
sm.dispose()
