# Maple Road : Maple Tree Hill (4000011)  Map for Explorer Tutorial

UNK_NPC = 10300  # NPC name - "???"

# Explorer - Tutorial Skipper
def skip_tutorial():
    MAPLE_ADMINISTRATOR = 2007

    quests_to_complete = [
        32202,  # Mystical Maple Tree
        32203,  # The New Explorer
        32215  # A Strange Explorer
    ]

    map_to_warp = 4000032  # End of
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


if not sm.hasQuest(32202) or not sm.hasQuestCompleted(32203):
    skip_tutorial()
    sm.lockInGameUI(True)
    sm.showFieldEffect("maplemap/enter/10000", 0)
    sm.sendDelay(1000)

    sm.spawnNpc(UNK_NPC, -240, 220)
    sm.showNpcSpecialActionByTemplateId(UNK_NPC, "summon", 0)
    sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 900, 0, -120, 0, sm.getNpcObjectIdByTemplateId(UNK_NPC), False, 0)
    sm.sendDelay(1800)

    sm.moveNpcByTemplateId(UNK_NPC, False, 1000, 100)
    sm.moveCamera(False, 200, 200, 200)

    # The delay is for letting the Npc move
    sm.sendDelay(3000)

    sm.moveCamera(True, 0, 0, 0)

    sm.sendDelay(900)

    sm.setSpeakerID(0)
    sm.setSpeakerType(3)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendNext("Who was that girl? Why did she run away when she saw me?")
    sm.sendNext("Maybe I'll follow her..")

    sm.removeNpc(UNK_NPC)
    sm.completeQuestNoRewards(32202)
    sm.lockInGameUI(False)
    sm.dispose()
