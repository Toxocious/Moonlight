# id 58963 ([Hieizan Temple] Jars at Rest 2), field 811000018
sm.setSpeakerID(9130108) # Mysterious Boy
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9130107) # Mysterious Boy
sm.sendNext("Just place these jars on the stone altar in front of the east tower over there.\r\nSimple, right? I've heard that that area is the best spot... ")
sm.sendSay("Did you know? There are a lot of souls floating around in this temple.")
sm.setParam(16)
sm.sendSay("(You feel something strange. He doesn't seem like the same boy you met earlier.)")
sm.setParam(4)
res = sm.sendAskYesNo("Now, go on. Place the jars on that altar.  ")
sm.startQuest(parentID)
sm.sendSayOkay("Simply click the altar. ")
