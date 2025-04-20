# id 34818 (Escape from the Academy), field 940202032
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)
sm.removeOverlapScreen(1000)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendNext("#face2#Thanks Illium! What happened to you? And what's that crystal?")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face3#This is the Elder Crystal. Agate gave it to me and told me to protect it.")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face2#Why can't she protect it?")
sm.blind(True, 200, 0, 0, 0, 1300)
sm.sendDelay(1600)
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendNext("#face4#I'll have to fill you in on the details later, but... she's... gone.")
sm.blind(False, 0, 0, 0, 0, 1300)
sm.sendDelay(1600)
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendNext("#face3#No!")
sm.setInnerOverrideSpeakerTemplateID(3001311) # Sinaria
sm.sendSay("#face2#How could this have happened?")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face3#I don't know what I can do, but I'll help you!")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face4#Morian, this is my responsibility. I can't put you in danger.")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face3#But...")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face4#We can argue this later. Right now, we need to get out of here. We're all in a lot of danger!")
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)
sm.removeOverlapScreen(1000)
sm.sendDelay(100)
sm.completeQuestNoCheck(parentID)
sm.sendDelay(100)
sm.createQuestWithQRValue(parentID, "exp=1")
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/02/21/17/13")
sm.lockInGameUI(False, True)
