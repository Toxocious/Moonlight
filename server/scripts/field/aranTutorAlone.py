# Aran - Tutorial Skipper
def skip_tutorial():
	COMBAT_STEP = 20001295
	MAPLE_ADMINISTRATOR = 2007

	quests_to_complete = [
		21000, # Find the Missing Kid 1
		21001, # Find the Missing Kid 2
		21010, # The Return of the Hero
		21011, # The Missing Weapon
		21012, # Abilities Lost
		21014, # Lilin's Account
		21015, # Basic Fitness Training 1
		21016, # Basic Fitness Training 2
		21017, # Basic Fitness Training 3
		21018, # Basic Fitness Test
		21100, # The Five Heroes
	]

	map_to_warp = 140000000 # Rien
	target_level = 10

	sm.setSpeakerID(MAPLE_ADMINISTRATOR)
	sm.removeEscapeButton()
	sm.lockInGameUI(True)

	if sm.sendAskYesNo("Would you like to skip the tutorial quest line and instantly arrive at #m" + str(map_to_warp) + "#?"):
		if sm.getChr().getLevel() < target_level:
			sm.addLevel(target_level - sm.getChr().getLevel())

		for quest in quests_to_complete:
			sm.completeQuestNoRewards(quest)
		
		sm.giveSkill(COMBAT_STEP, 1, 1)
		sm.warp(map_to_warp)

	sm.createQuestWithQRValue(21019, "noskip")
	sm.lockInGameUI(False)
	sm.dispose()

if sm.getQRValue(21019) != "noskip":
	skip_tutorial()