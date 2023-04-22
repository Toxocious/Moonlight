# Evan Intro | Dream World: Dream Forest Entrance (900010000)

# Evan - Tutorial Skipper
def skip_tutorial():
    MAPLE_ADMINISTRATOR = 2007

    quests_to_complete = [
        22000,  # Strange Dream
        22001,  # Feeding Bull Dog
        22002,  # Sandwich for Breakfast
        22003,  # Delivering the Lunch Box
        22004,  # Fixing the Fence
        22005,  # Rescuing the Piglet
        22006,  # Returning the Empty Lunch Box
        22007,  # Collecting Eggs
        22008,  # Chasing away the Foxes
        22009,  # Verifying the Farm Situation
        22010,  # Strange Farm
    ]

    map_to_warp = 100030102  # Front Yard
    target_level = 10

    sm.setSpeakerID(MAPLE_ADMINISTRATOR)
    sm.removeEscapeButton()
    sm.lockInGameUI(True)

    if sm.sendAskYesNo("Would you like to skip the tutorial questline and instantly arrive at #m" + str(map_to_warp) + "#?"):
        if sm.getChr().getLevel() < target_level:
            sm.addLevel(target_level - sm.getChr().getLevel())

        for quest in quests_to_complete:
            sm.completeQuestNoRewards(quest)

        sm.giveItem(1372107)  # Beginner Magician's Wand
        sm.resetAP(False, sm.getChr().getJob())
        # sm.addSP(3, True)
        sm.warp(map_to_warp)

    sm.createQuestWithQRValue(22011, "noskip")
    sm.lockInGameUI(False)
    sm.dispose()


if sm.getQRValue(22011) != "noskip":
    skip_tutorial()
