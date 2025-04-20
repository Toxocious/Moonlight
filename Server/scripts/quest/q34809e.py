# id 34809 (A Stranger's Cries), field 402000513
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendNext("#face0#Carnelian...?")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face2#Sniff... Morian... Illium...")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#What happened?")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face2#Well... my crystal... sniffle...")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(parentID, "exp=1")
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/02/21/16/30")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#It's okay, Carnelian. Just take a deep breath and tell us what's wrong.")
sm.createQuestWithQRValue(16700, "count=152;date=20190221")
sm.createQuestWithQRValue(16700, "count=153;date=20190221")
sm.createQuestWithQRValue(16700, "count=154;date=20190221")
sm.lockInGameUI(False, True)
