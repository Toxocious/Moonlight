response = sm.sendAskYesNo("Would you like to go to Pantheon?")

if response:
    sm.warp(400000001, 1)