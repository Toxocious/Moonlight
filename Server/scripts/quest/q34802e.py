# id 34802 (Collect Grossular), field 402000526
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001302) # Professor Kalsat
sm.sendNext("#face0#Late on the first day. That's not an encouraging start.")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Well, I...")
sm.setInnerOverrideSpeakerTemplateID(3001302) # Professor Kalsat
sm.sendSay("#face0#Don't let it happen again.")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(parentID, "class1=1;out=2;exp=1")
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/02/21/16/06")
sm.startQuest(11620)
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#Hm...")
sm.sendSay("#face0#Your next class will teach you magical combat.")
sm.lockInGameUI(False, True)
