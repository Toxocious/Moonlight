if sm.getFieldID() != 910000000 and sm.sendAskYesNo("Do you want to move to Free Market?"):
    sm.setReturnField()
    sm.warp(910000000, 2)