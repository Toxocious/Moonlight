SKIPPER = 1096000
REITING = 1096001

if sm.sendAskYesNo("Would you like to skip the intro?"):
    sm.completeQuestNoRewards(2568)
    if sm.getChr().getLevel() < 10:
        sm.addLevel(10 - sm.getChr().getLevel())
    sm.warp(912060500)
    sm.dispose()

sm.lockInGameUI(True)
sm.curNodeEventEnd(True)
sm.forcedInput(0)
sm.completeQuest(2573)

sm.spawnNpc(SKIPPER, 2209, -107)
sm.showNpcSpecialActionByTemplateId(SKIPPER, "summon", 0)

sm.spawnNpc(REITING, 2046, -62)
sm.showNpcSpecialActionByTemplateId(REITING, "summon", 0)
sm.forcedInput(2)
