# Stump at the Room of Maze
# Cave of Life | Room of Maze
# Warps players into the Horntail jump quest

if sm.isPartyLeader(): 
	sm.warpInstanceIn(240050101, 1)
else:
	sm.systemMessage("Only your party leader may enter..")