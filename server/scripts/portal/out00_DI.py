
response = sm.sendAskYesNo("Do you wish to leave the battlefield")

if response:
    sm.warpInstanceOutParty(940020000)