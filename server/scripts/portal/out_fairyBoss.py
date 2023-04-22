# Portal for leaving Ephenia

EPHENIAS_HIDING_PLACE = 2133006 # NPC ID
FAIRY_FOREST_2 = 300030300 # MAP ID

if not sm.hasMobsInField():
    sm.clearPartyInfo(FAIRY_FOREST_2)
else:
    sm.setSpeakerID(EPHENIAS_HIDING_PLACE)
    response = sm.sendAskYesNo("Are you sure you want to leave?")

    if response:
        sm.clearPartyInfo(FAIRY_FOREST_2)