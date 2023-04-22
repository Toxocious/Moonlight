# id 58947 ([Hieizan Temple] Jars at Rest 1), field 811000018
sm.setSpeakerID(9130108) # Mysterious Boy
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9130107) # Mysterious Boy
sm.sendNext("You got here faster than I thought. Figures, since you aren't carrying anything.")
sm.sendSay("Whoof! As for me, I'm all worn out from carrying ALL these jars. My arms are about to FALL OFF! ")
sm.sendSay("...Which means you should offer to carry these for me. Here.")
sm.completeQuestNoCheck(parentID)
sm.sendPrev("Be careful with these. I don't know what we'd do if you broke them.")
sm.createQuestWithQRValue(18418, "B=35638")
