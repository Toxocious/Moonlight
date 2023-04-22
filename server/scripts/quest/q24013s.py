if sm.getChr().getJob() == 2311:
    sm.jobAdvance(2312)
    sm.setSpeakerID(1033210)
    sm.sendSayOkay("I have advanced you to fourth job.")
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
sm.dispose()
