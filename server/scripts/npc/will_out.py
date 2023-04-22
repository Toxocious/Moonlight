
response = sm.sendAskYesNo("Are you sure you want to leave? Your whole party will be ported out.")

if response:
    sm.warpInstanceOut(450007240)
