map = 200090600

response = sm.sendAskYesNo("Would you like to go to Edelstein?")

if response:
    sm.warp(map, 0)
