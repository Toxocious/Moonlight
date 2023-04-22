# 240091500

CHINO = 2210004
TIME = 20

if sm.hasQuestCompleted(31342) or sm.hasQuest(31342):
    sm.warpInstanceIn(240091600, 0)
    sm.setInstanceTime(TIME, 240092000, 0)
else:
    sm.setSpeakerID(CHINO)
    sm.sendSayOkay("Where do you think you're going buddy? That lift is for me.\r\n\r\n#fs 18##eNO ONE ELSE!")