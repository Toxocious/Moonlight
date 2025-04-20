# Quick Move Pokemon

PCcafe = 193000000

if sm.getFieldID() == PCcafe:
    sm.sendNext("I am not coded\r\n#b")
elif sm.sendAskYesNo("Would you like to be teleported to the Internet Cafe?\r\n#b"):
    sm.warp(193000000)