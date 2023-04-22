from net.swordie.ms.constants import QuestConstants

sm.waitForMobDeath(9300510)
sm.spawnNpc(9073022, -380, -405)
if not "m4=2" in sm.getQRValue(QuestConstants.SILENT_CRUSADE_WANTED_TAB_1):
    sm.addQRValue(QuestConstants.SILENT_CRUSADE_WANTED_TAB_1, "m4=2")