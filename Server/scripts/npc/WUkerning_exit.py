# Nella (9076112) | First Time Together Stage 1 Exit
sm.setSpeakerID(parentID)
response = sm.sendAskYesNo("Are you sure you want to leave your party behind and go back to the waiting room?")

if response == 1:
    sm.warpInstanceOut(933010000)