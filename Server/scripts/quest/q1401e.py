# 1401 - Warriors of Perion

sm.setSpeakerID(1022000)  # Dances with Balrog
response = sm.sendAskYesNo("So you want to become a #bWarrior#k?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.jobAdvance(100)
    sm.resetAP(False, 100)
    sm.giveItem(1302182)
    sm.sendSayOkay("You are now a #bWarrior#k.")
