# id 34812 (A Special Day), field 402000530
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendNext("#face1#Illium, today's activity will be a bit unusual. Please come to #m402000501#.")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
res = sm.sendAskAccept("#face0#Would you like to go to #m402000501# right away?\r\n#b(If you accept, you'll travel there automatically.)")
sm.startQuest(parentID)
sm.warp(402000501)
