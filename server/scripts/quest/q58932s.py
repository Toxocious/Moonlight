# id 58932 ([Hieizan Temple] Fill the Blue Jar), field 811000014
sm.setSpeakerID(9130107) # Mysterious Boy
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9130107) # Mysterious Boy
sm.sendNext("You did a great job filling up this red jar! I just know that you will do the same with the blue jar.  ")
res = sm.sendAskYesNo("Hope you can still help with filling the jar... This is all for the souls of those men...")
sm.startQuest(parentID)
sm.sendNext("The second jar will fill if you eliminate about 150 #o9450032:# monsters in #m811000016:#. ")
sm.sendSay("Please hurry to get this job done. ")
sm.setParam(16)
sm.sendPrev("Get this job done...?")
