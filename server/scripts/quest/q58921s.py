# id 58921 ([Hieizan Temple] Jars at Rest 1), field 811000014
sm.setSpeakerID(9130107) # Mysterious Boy
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9130107) # Mysterious Boy
sm.sendNext("Here, I wrapped up the jars you gave me with my bag cloth.")
sm.sendSay("Isn't this cloth nice? My mother made it herself, hee hee.")
sm.setParam(16)
sm.sendSay("(You felt it earlier, too... The floral prints are actually kind of unpleasant. Ugly. But you hold your tongue.)")
sm.setParam(4)
res = sm.sendAskYesNo("Now, I'll meet you at #m811000018:#. That's where you want to place the jars. ")
sm.startQuest(parentID)
sm.sendSayOkay("#m811000018:# is just past #m811000017:#. I'll see you there.")
