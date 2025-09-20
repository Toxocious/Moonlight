# author @Manu13
# Description: Script GMS-Like for Veteran Medal
MEDAL = 1142004
QUEST = 29400
DALAIR = 9000040

sm.setSpeakerID(DALAIR)
sm.sendSayOkay(
    "#b#eTitle Challenge - Veteran Hunter#n\r\n#kHunt #r100,000#k monsters that are higher level than you within 30 days!  The Monk of Honor, Dalair, is looking for a clever hunter who has stamina.  Anyone who completes this task will receive the title, #e#bVeteran Hunter#k#n.  I heard that the person with the highest score will receive another title called #e#bLegendary Hunter (Special Title)#n#k...")
date = sm.getCurrentDateAsString()
sm.setQRValue(QUEST, "mobCount=0;maxMobCount=100000;startTime=" + date + ";endTime=0;state=0")
sm.startQuestNoCheck(QUEST)
sm.dispose()
