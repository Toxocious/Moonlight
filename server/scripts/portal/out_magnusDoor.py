sm.setSpeakerID(3001021)
response = sm.sendAskYesNo("Do you wish to leave the battlefield?")

if response:
    sm.warpInstanceOutParty(401060000)
    sm.dispose()
