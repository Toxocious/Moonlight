# id 34814 (A Divine Festival 2), field 402000529
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendNext("#face0#If I keep improving my sculpture, I'll need more metal.")
sm.sendSay("#face0#I'll have to go to the #m402000510# first to get\r\n#i4036169# #b#t4036169##k items.\r\nThis time, #b10#k should be enough.")
sm.setSpeakerType(4)
sm.setSpeakerID(3001339) # Illium Statue
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
res = sm.sendAskAccept("#face0#Would you like to go to the #b#m402000510##k right away?\r\n#b(If you accept, you'll travel there automatically.)")
sm.startQuest(parentID)
sm.warp(402000510)
