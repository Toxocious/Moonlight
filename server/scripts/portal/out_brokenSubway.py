STARLING = 9120221

if sm.hasQuestCompleted(1602):
    sm.warpInstanceOut(103020000, 2)

else:
    sm.setSpeakerID(STARLING)
    response = sm.sendAskYesNo("Excuse me? \r\nAre you just going to leave me?")

    if response:
        sm.warpInstanceOut(103020000, 2)