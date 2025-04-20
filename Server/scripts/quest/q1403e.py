# 1403 - Athena Pierce

sm.setSpeakerID(1012100)  # Athena
response = sm.sendAskYesNo("So you want to become an #bArcher#k?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.jobAdvance(300) # Archer
    sm.resetAP(False, 300)
    sm.giveItem(1452051, 1)
    sm.giveItem(1462001, 1)
    sm.giveItem(2060000, 500)
    sm.giveItem(2061000, 500)
    sm.sendSayOkay("You are now an #bArcher#k!")
