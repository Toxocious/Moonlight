sm.setSpeakerID(1033210) # Great Spirit
response = sm.sendAskYesNo("Are you ready to take on great power?")

if response:
    sm.startQuest(parentID)
sm.dispose()
