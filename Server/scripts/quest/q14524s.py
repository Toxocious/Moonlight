sm.setSpeakerID(9400534) # Goddess Avaracia
sm.sendNext("Are you ready to begin?")

sm.setPlayerAsSpeaker() # Player
sm.sendNext("What are you talking about? Who are you?")

sm.setSpeakerID(9400534) # Goddess Avaracia
response = sm.sendAskYesNo("There isn't much time to explain, will you help me?")

if response == 1:
    sm.sendNext("Please go eliminate 100 #r#o9390010##k, They need to be dealt with.")
    sm.startQuest(parentID)
else:
    sm.sendSayOkay("I guess you aren't up for the task")
sm.dispose()