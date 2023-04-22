# id 34808 (A Divine Festival 1), field 402000526
sm.setSpeakerID(3001335) # Professor Lutil
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
res = sm.sendAskAccept("#face0#To create your sculptures, you'll need #i4036167# #b#t4036167##k #bx20#k!")
sm.startQuest(parentID)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001305) # Professor Lutil
sm.sendNext("#face0#You can only get #i4036167# #b#t4036167##k in #r#o2400402##k. I'll transport you now.")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#I recommend #busing the Maple Guide#k when you return.")
sm.warp(402000514)
