# id 58931 ([Hieizan Temple] Fill the Red Jar), field 811000014
sm.setSpeakerID(9130107) # Mysterious Boy
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9130107) # Mysterious Boy
sm.sendNext("A jar of souls... It sounds terrible, but I guess it's the best we can do. ")
res = sm.sendAskYesNo("Would you please help me gather those poor souls? ")
sm.startQuest(parentID)
sm.sendNext("Thank you. I feel a lot better. ")
sm.sendSay("Eliminate 100 #o9450031:# monsters in #m811000015:# to fill this jar. ")
sm.sendPrev("I'll leave it to you. ")
