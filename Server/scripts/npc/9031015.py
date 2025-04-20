#Quick move ardent and Pflame NPC

Ardentmill = 910001000

if sm.getFieldID() == Ardentmill:
    sm.sendNext("I am not coded\r\n#b")
elif sm.sendAskYesNo("Would you like to teleported to Ardentmill?\r\n#b"):
    sm.setReturnField()
    sm.warp(910001000)