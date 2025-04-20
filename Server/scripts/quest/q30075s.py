# id 30075 (Move to the Castle Rear Entrance), field 106030000
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1302007) # Head Security Officer
res = sm.sendAskYesNo("I'll send you to the Castle's Rear Entrance.\r\nReady?\r\n\r\n#b(You will automatically travel to the Castle's Rear Entrance if you accept.)#k")
sm.warp(106030302)
