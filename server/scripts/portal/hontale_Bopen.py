# Cave of Life | 1st Room of Maze
# Continue Portal

nextMap = chr.getFieldID() + 1
portal = 0

if nextMap > 240050105:
	nextMap = 240050200
	portal = 1

if sm.isPartyLeader():
	if sm.hasMobsInField():
		sm.systemMessage("The map must be clear before proceeding..")
	else:
		# TODO reset map for next party
		sm.invokeForParty("warp", nextMap, portal)
else:
	sm.systemMessage("Only the party leader may proceed..")