response = sm.sendAskYesNo("Would you like to go back to Victoria Island?")

if response:
    sm.warp(104020000, 0)