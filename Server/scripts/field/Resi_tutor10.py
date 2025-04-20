# Resistance - Tutorial Skipper
def skip_tutorial():
	MAPLE_ADMINISTRATOR = 2007

	quests_to_complete = [
		23000, # Spill It, Headmaster!
		23001, # Request from a Kindergarten Teacher
		23002, # Request from a Police Officer
		23003, # Request from a Doctor
		23004, # Request from a Streetsweeper
		23005, # Request from a Mascot
		23010, # Mysterious Invitation
	]

	map_to_warp = 310010000 # Secret Plaza
	target_level = 10

	sm.setSpeakerID(MAPLE_ADMINISTRATOR)
	sm.removeEscapeButton()
	sm.lockInGameUI(True)

	if sm.sendAskYesNo("Would you like to skip the tutorial questline and instantly arrive at #m" + str(map_to_warp) + "#?"):
		if sm.getChr().getLevel() < target_level:
			sm.addLevel(target_level - sm.getChr().getLevel())

		for quest in quests_to_complete:
			sm.completeQuestNoRewards(quest)
		
		sm.warp(map_to_warp)
		
	sm.lockInGameUI(False)
	sm.dispose()

skip_tutorial()