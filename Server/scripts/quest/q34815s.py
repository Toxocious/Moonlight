# id 34815 (A Divine Festival 3), field 402000529
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendNext("#face0#Let's find a rarer metal this time, #i4036170# #b#t4036170##k. We should only need about #b10#k.")
sm.setSpeakerType(4)
sm.setSpeakerID(3001339) # Illium Statue
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
res = sm.sendAskAccept("#face0#Would you like to go to the #b#m402000509##k right away?\r\n#b(If you accept, you'll travel there automatically.)")
sm.startQuest(parentID)
sm.warp(402000509)
