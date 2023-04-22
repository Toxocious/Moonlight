SEAL_OF_TIME_1 = 2159363
SEAL_OF_TIME_2 = 2159364
SEAL_OF_TIME_3 = 2159365
SEAL_OF_TIME_4 = 2159366
SEAL_OF_TIME_5 = 2159367

if not sm.hasQuest(25674) and sm.hasQuest(25670) and sm.hasQuest(25671) and sm.hasQuest(25672) and sm.hasQuest(25673):
    sm.createQuestWithQRValue(25674, "1", False)
    sm.showFieldEffect("lightning/screenMsg/7")
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("All the seals should be active now.")
    sm.setFuncKeyByScript(False, 20041222, 0)
    sm.removeNpc(SEAL_OF_TIME_1)
    sm.removeNpc(SEAL_OF_TIME_2)
    sm.removeNpc(SEAL_OF_TIME_3)
    sm.removeNpc(SEAL_OF_TIME_4)
    sm.removeNpc(SEAL_OF_TIME_5)
    # kill mobs
    sm.warp(927020072, 0)