map = 200090701

response = sm.sendAskYesNo("Do you want to go to Edelstein?")

if response:
    sm.warp(map, 0)
