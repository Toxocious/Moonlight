# id 34816 (A Divine Festival 4), field 402000529
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendNext("#face0#It's probably best to stay focused on the statue. I need at least 20 more\r\n#i4036171# #b#t4036171##k items.")
sm.setSpeakerType(4)
sm.setSpeakerID(3001339) # Illium Statue
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
res = sm.sendAskAccept("#face0#Would you like to go to #b#m402000503##k right away?\r\n#b(If you accept, you'll travel there automatically.)")
sm.startQuest(parentID)
sm.warp(402000504)
