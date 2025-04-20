# id 34805 (Making a Crystal Gate 1), field 402000530
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001304) # Professor Citrine
sm.sendNext("#face0#Let me know when you've found a partner, and I'll give you the assignment.")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#I will try to identify a good team matchup for you, Sir.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Thanks, Ex.")
sm.setSpeakerID(3001334) # Professor Citrine
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001304) # Professor Citrine
if sm.sendAskAccept("#face0#Has everyone found a teammate?\r\nThen begin gathering 20 #i4036164# #t4036164# items, so you can craft your gates!"):
    sm.startQuest(parentID)
    sm.setSpeakerType(3)
    sm.setParam(37)
    sm.sendNext("#face0#Don't forget what you've learned! You can only get #i4036164# #b#t4036164##k items from #r#o2400401##k.\r\nI'll transport you now. Good luck!")
    sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
    sm.sendSay("#face0#I recommend #busing the Maple Guide#k when you return.")
    sm.warp(402000519)