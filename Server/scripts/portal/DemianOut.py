# Damien leave NPC

WORLD_TREE_SUMMIT = 105300303

BLACK_HEAVEN_CORE_ENTRANCE = 350060300
BLACK_HEAVEN_CORE_LAST_HARD = 350060600

sm.setSpeakerID(1530621)
response = sm.sendAskYesNo("Are you sure you want to leave the battlefield?")
if response:
	sm.warpInstanceOutParty(WORLD_TREE_SUMMIT)
else:
	sm.dispose()


