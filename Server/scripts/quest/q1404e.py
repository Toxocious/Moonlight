# 1404 - Thieves of Kerning City

sm.setSpeakerID(1052001)
response = sm.sendAskYesNo("So you want to become a #bThief#k?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.jobAdvance(400)  # Thief
    sm.resetAP(False, 400)
    sm.giveItem(2070000, 500)
    sm.giveItem(1332063, 1)
    sm.giveItem(1472061, 1)
    sm.sendSayOkay("You are now a #bThief#k.")
