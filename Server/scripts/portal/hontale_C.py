# Cave of Life | Cave of Choice
# Warps to next Horntail map

if sm.isPartyLeader():
	if sm.hasMobsInField():
		sm.systemMessage("The map must be clear before proceeding..")
	else:
		# TODO reset map for next party
		sm.invokeForParty("warp", chr.getFieldID() + 100, 1)
else:
	sm.systemMessage("Only the party leader may proceed..")