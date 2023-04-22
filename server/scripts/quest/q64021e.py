# id 64021 ([MONAD: The First Omen] Town Reconstruction), field 867200400
sm.lockInGameUI(True, False)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendNext("#face0#Ah, you're here! ")
sm.setInnerOverrideSpeakerTemplateID(9400582) # Cayne
sm.sendSay("#face0#How did you get here before us? ")
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendSay("#face0#Hah, since there was nowhere to lay him down, I brought him here. ")
sm.setInnerOverrideSpeakerTemplateID(9400580) # Alika
sm.sendSay("#face1##h0#, thanks for working so hard out there in the cold. ")
sm.setInnerOverrideSpeakerTemplateID(9400582) # Cayne
sm.sendSay("#face0#What about me?! ")
sm.setParam(57)
sm.sendSay("#bNot at all. ")
sm.sendSay("#bPeytour, take the wood. ")
sm.completeQuestNoCheck(parentID)
sm.startQuest(64023)
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendSay("#face0#So! Shall we start? ")
sm.sendSay("#face0#Ah, right. #h0#, can I ask you for one more favor? ")
sm.sendSay("#face0#I asked Slaka to make some rope, but he hasn't gotten back to me. ")
sm.sendSay("#face0#Can you get the rope from him for me? ")
sm.setParam(57)
sm.sendSay("#bOf course. ")
sm.setParam(37)
sm.sendSay("#face0#He should be at the entrance of town. He'll be the sour-looking one, so you should have no trouble finding him.")
sm.lockInGameUI(False, True)
