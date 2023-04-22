# 1405 - Pirates of the Nautilus

sm.setSpeakerID(1090000)
response = sm.sendAskYesNo("So you want to become a #bPirate#k?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.jobAdvance(500)  # Pirate
    sm.resetAP(False, 500)
    sm.giveItem(1492014)
    sm.giveItem(1482014)
    sm.giveItem(2330006, 500)
    sm.sendSayOkay("You are now a #bPirate#k.")
