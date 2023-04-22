# Dao (9000080) | Ravana's Golden Altar

if sm.getFieldID() == 252030100:
    if sm.sendAskYesNo("Would you like to leave?"):
		sm.warpInstanceOut(252030000)
else:
	sm.sendSayOkay("Enter if you wish.")