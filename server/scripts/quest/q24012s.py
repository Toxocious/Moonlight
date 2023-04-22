if sm.getChr().getJob() == 2310:
    sm.jobAdvance(2311)
    sm.setSpeakerID(1033210)
    sm.sendSayOkay("I have advanced you to third job.")
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
sm.dispose()
