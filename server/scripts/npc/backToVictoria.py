map = 104020000
if sm.getFieldID() != 120040000:
    map = 120040000


if sm.sendAskYesNo("Would you like to go to #m" + str(map) + "#?"):
    sm.warp(map, 0)
