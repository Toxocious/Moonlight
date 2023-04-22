if not sm.hasQuestCompleted(23611):
    sm.setSpeakerID(2300001)
    response = sm.sendAskYesNo("You can't Return to Veritas until you complete your 2nd Job Advancement. Until then, you'll have to use the portal at the Aquarium. Are you sure you want to leave?")
    if response:
        sm.warp(310000000)
else:
    sm.warp(310000000)
