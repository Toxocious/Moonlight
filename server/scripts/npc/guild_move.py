response = sm.sendAskYesNo("Would you like to go to the guild headquarters?")

if response:
    sm.warp(200000301, 0)
