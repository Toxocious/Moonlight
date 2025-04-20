# id 34300 ([Lachelein] Festival of Dreams), field 450003000
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(3003201) # Protective Mask
sm.sendNext("Are you from outside?")
sm.setParam(2)
sm.sendSay("?")
sm.setParam(4)
sm.sendSay("You must be careful, this place is dangerous. Oh no, they're here!.")
sm.completeQuestNoCheck(parentID)
sm.warp(450003710)
