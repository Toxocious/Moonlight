response = sm.sendAskYesNo("Are you sure you want to leave?")

if response:
    sm.warpInstanceOutParty(401052104)
    sm.dispose()
