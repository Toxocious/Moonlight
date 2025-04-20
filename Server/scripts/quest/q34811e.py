# id 34811 (Making a Crystal Gate 2), field 402000530
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001304) # Professor Citrine
sm.sendNext("#face0#That is all for today's class. Those of you who didn't complete your assignments, please follow me.")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face3#Go on without me. I'm done for...")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Aw, you'll be all right. See you tomorrow, Morian.")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(parentID, "room=1;exp=1")
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/02/21/16/40")
sm.lockInGameUI(False, True)