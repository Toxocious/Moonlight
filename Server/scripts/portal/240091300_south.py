CHRISTOPHER = 2210012

if sm.hasQuestCompleted(31351): # Clean up 7
    sm.warp(240091400, 1)
else:
    sm.setSpeakerID(CHRISTOPHER)
    sm.sendSayOkay("Oh Hey! No no no..\r\n"
                   "You don't want to go in there! That place is full of wasps and really strong ones too.. "
                   "It seems as if they are draining energy from the Mountain.\r\n")