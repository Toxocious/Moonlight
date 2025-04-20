SEAL_CHECKER = 9300535
SEAL_OF_TIME = 2159367

if not sm.hasQuest(25671):
    sm.showFieldEffect("lightning/screenMsg/6")
    sm.createQuestWithQRValue(25671, "1", False)
    if sm.hasQuest(25670) and sm.hasQuest(25672) and sm.hasQuest(25673):
        sm.spawnMob(SEAL_CHECKER, -54, -80, False)
        sm.spawnNpc(SEAL_OF_TIME, -54, -80)
        sm.showNpcSpecialActionByTemplateId(SEAL_OF_TIME, "summon", 0)
        sm.flipDialoguePlayerAsSpeaker()
        sm.sendSayOkay("The final seal is below the central staircase. I'm almost done.")

