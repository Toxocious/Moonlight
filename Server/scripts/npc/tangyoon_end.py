response = sm.sendAskYesNo("Are you sure you want to leave?")
if response:
    if not sm.getParty() is None:
        sm.warpInstanceOut(910002000)
        for partyMembers in sm.getParty().getMembers():
            sm.setQRValue(partyMembers.getChr(), GameConstants.LORD_PIRATE_QUEST, "0")
    else:
        sm.warp(910002000, 0)
        sm.setQRValue(GameConstants.LORD_PIRATE_QUEST, "0")