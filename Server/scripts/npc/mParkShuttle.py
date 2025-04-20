# Monster Park Shuttle

map = 100000000
if sm.getFieldID() != 951000000:
    map = 951000000


response = sm.sendAskYesNo("Would you like to go to #m" + str(map) + "#?")

if response:
    sm.warp(map, 0)
