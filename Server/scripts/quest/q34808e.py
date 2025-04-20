# id 34808 (A Divine Festival 1), field 402000526
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001305) # Professor Lutil
sm.sendNext("#face0#Well done, everyone! Be sure to display the sculptures you crafted in the town square at the end of the quarter. All of that hard work deserves to be admired!")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(parentID, "exp=1;d2=1")
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/02/21/16/37")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face2#Oh! I just remembered I have another assignment!")
sm.sendSay("#face0#Great teamwork today! I'll see you later.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face9#Okay! See you!")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#I'll guide you to your next class.")
sm.lockInGameUI(False, True)
