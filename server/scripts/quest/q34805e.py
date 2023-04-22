# id 34805 (Making a Crystal Gate 1), field 402000530
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001304) # Professor Citrine
sm.sendNext("#face0#It's nice to see you making an honest effort, but remember, I expect to see further improvement in the future.")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(parentID, "exp=1")
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/02/21/16/19")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face0#That worked out great! Let's be partners next time too!")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face9#Okay! See you soon!")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#I'll guide you to the Headmistress's Office now that today's lessons are complete.")
sm.lockInGameUI(False, True)
