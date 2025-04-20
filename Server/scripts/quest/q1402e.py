# 1402 - Magicians of Ellinia

sm.setSpeakerID(1032001)  # Grendel the Really Old
response = sm.sendAskYesNo("So you want to become a #bMagician#k?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.jobAdvance(200)  # Magician
    sm.resetAP(False, 200)
    sm.giveItem(1372043)
    sm.sendSayOkay("You are now a #bMagician#k.")
