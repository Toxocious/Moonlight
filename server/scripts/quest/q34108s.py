KAO = 3003131

sm.setSpeakerID(KAO)
sm.sendNext("I'm afraid there's no way around this massive rocky cliff. We'll have to climb up the cliff.")
if sm.sendAskAccept("#h0#, you agree, right? Then let's climb it."):
    sm.startQuest(parentID)
    sm.completeQuest(parentID)