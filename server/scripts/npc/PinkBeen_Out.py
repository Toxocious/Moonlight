if sm.getFieldID() == 270050100:
    response = sm.sendAskYesNo("Are you sure you want to leave the battlefield?")

    if response:
        sm.warpInstanceOutParty(270050000)
if sm.getFieldID() == 270051100:
    response = sm.sendAskYesNo("Are you sure you want to leave the battlefield?")

    if response:
        sm.warpInstanceOutParty(270050000)