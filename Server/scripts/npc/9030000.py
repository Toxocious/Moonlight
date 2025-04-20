# Fredrick and quick move NPC

Freemarket = 910000000

if sm.getFieldID() == Freemarket:
    sm.getItemsFromTrunkEmployee()
elif sm.sendAskYesNo("Would you like to be teleported to the Free Market?\r\n#b"):
    sm.setReturnField()
    sm.warp(910000000)