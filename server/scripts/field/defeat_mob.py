from net.swordie.ms.constants import QuestConstants

CRUSADER_COIN = 4310018

dict = {
    #Mob Id : [Tab Quest ID, QR Value]
    9300477 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_1, "m0=2", 1],
    9300878 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_1, "m1=2", 1],
    9300879 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_1, "m2=2", 1],
    8090000 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_1, "m3=2", 1],
    9300510 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_1, "m4=2", 1],

    9300479 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_2, "m0=2", 2],
    9300480 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_2, "m1=2", 2],
    9300511 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_2, "m2=2", 2],
    9300512 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_2, "m3=2", 2],
    9300482 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_2, "m4=2", 2],

    9300475 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_3, "m0=2", 3],
    9300514 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_3, "m1=2", 3],
    9300880 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_3, "m2=2", 3],
    9300478 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_3, "m3=2", 3],
    9300513 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_3, "m4=2", 3],

    9300515 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_4, "m0=2", 4],
    9300516 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_4, "m1=2", 4],
    9300517 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_4, "m2=2", 4],
    9300518 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_4, "m3=2", 4],
    9300519 : [QuestConstants.SILENT_CRUSADE_WANTED_TAB_4, "m4=2", 4],
}

while sm.hasMobsInField():
    mob = sm.waitForMobDeath()
	mobid = mob.getTemplateId()
    if mobid in dict and not dict[mobid][1] in sm.getQRValue(dict[mobid][0]):
        sm.addQRValue(dict[mobid][0], dict[mobid][1])
		sm.dropItem(CRUSADER_COIN, (dict[mobid][2] * 2) - 5, mob) # crusader coin reward: (stage * 2) - 5
sm.dispose()