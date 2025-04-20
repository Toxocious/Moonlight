response = sm.sendAskYesNo("Would you like to travel to Shanghai?")

if response:
    sm.warp(701102000, 0)
