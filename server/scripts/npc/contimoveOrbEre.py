map = 200090020

response = sm.sendAskYesNo("Would you like to go #m" + str (map) + "m#?")

if response:
    sm.warp(map, 0)
