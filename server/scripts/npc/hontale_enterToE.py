# Encrypted Slate of the Squad(2083000) | Cave of Life, Cave Entrance

ROOM_OF_MAZE = 240050100

if sm.isPartyLeader():
	if sm.sendAskYesNo("The letters on the slate glitter and the backdoor opens. Do you want to go to the secret path?"):
		sm.invokeForParty("warp", ROOM_OF_MAZE)
else:
    sm.sendSayOkay("Only the party leader may proceed to the Horntail quest.")