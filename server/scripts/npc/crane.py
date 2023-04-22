map = 200090300
string = "Mu Lung?"
if sm.getFieldID() == 250000100:
    map = 200090310
    string = "Orbis?"
response = sm.sendAskYesNo("Would you like to go to " + (string))

if response:
    sm.warp(map, 0)