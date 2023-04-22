# Von Leon : Lion King

VON_LEON_NPC = 2161000

if sm.sendAskYesNo("Are you the warriors who came to defeat me? Or are you from the Anti Black Mage Alliance? It doesn't matter who you are ...There's no need for chitchatting if we are sure about each other's purpose...\r\nBring it on, you fools!"):
	sm.removeNpc(VON_LEON_NPC)
	if sm.getFieldID() == 211070100:
		sm.spawnMob(8840007, 28, -181, False)
	elif sm.getFieldID() == 211070102:
		sm.spawnMob(8840000, 28, -181, False)