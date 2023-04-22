# id 34812 (A Special Day), field 402000501
sm.lockInGameUI(True, False)
sm.sendDelay(100)
sm.completeQuestNoCheck(parentID)
sm.sendDelay(100)
sm.createQuestWithQRValue(parentID, "exp=1")
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/02/21/16/41")
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001306) # Soldier
sm.sendNext("#face0#Hey, kid! You running an errand for the Lady Agate?")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Huh? Why? Do you need something?")
sm.lockInGameUI(False, True)
