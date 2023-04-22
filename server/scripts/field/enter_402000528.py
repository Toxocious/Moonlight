# id 402000528 (null), field 402000528
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.blind(True, 250, 0, 0, 0, 0)
sm.spawnNpc(3001309, -20, 63)
sm.showNpcSpecialActionByTemplateId(3001309, "summon", 0)
sm.spawnNpc(3001300, 1506, 22)
sm.showNpcSpecialActionByTemplateId(3001300, "summon", 0)
sm.sendDelay(1000)
sm.moveNpcByTemplateId(3001300, True, 1000, 150)
sm.forcedMove(True, 1000)
sm.zoomCamera(0, 2000, 0, -100, 63)
sm.sendDelay(1000)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001309) # Darius
sm.sendNext("#face0#What were you thinking enrolling that miscreant child in the Academy? You rewarded him for behavior that could endanger us all.")
sm.blind(True, 255, 0, 0, 0, 0)
sm.sendDelay(1200)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.sendDelay(1400)
sm.sendDelay(1000)
sm.sendNext("#face0#These laws are in place for a reason.")
sm.sendSay("#face4#What if he's a spy for the High Flora? What if we're discovered. Worse, what if the Elder Crystal is discovered?")
sm.sendSay("#face4#We must strengthen security around the Crystal. Where should I station our men? We can't take any risks.")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face0#You needn't worry. The Crystal is safe.")
sm.sendSay("#face2#Hello, Illium.")
sm.zoomCamera(1000, 2000, 1000, 336, 63)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendNext("#face4#Oh! Hello...")
sm.flipNpcByTemplateId(3001309, False)
sm.sendDelay(30)
sm.setInnerOverrideSpeakerTemplateID(3001309) # Darius
sm.sendNext("#face1#You...")
sm.forcedMove(True, 300)
sm.moveNpcByTemplateId(3001300, True, 250, 150)
sm.zoomCamera(1500, 2000, 1500, -100, 63)
sm.sendDelay(1500)
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendNext("#face1#Darius, let's end our conversation here. You may take your leave.")
sm.flipNpcByTemplateId(3001309, True)
sm.sendDelay(30)
sm.setInnerOverrideSpeakerTemplateID(3001309) # Darius
sm.sendNext("#face4#As you wish.")
sm.flipNpcByTemplateId(3001309, False)
sm.sendDelay(30)
sm.moveNpcByTemplateId(3001309, False, 850, 150)
sm.sendDelay(3000)
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendNext("#face0#Illium, did you hear what Darius said?")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face8#Yes...")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#Some students today were less than welcoming to Illium today.")
sm.sendSay("#face0#It is not a good means of building camaraderie among peers.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face8#I'm sure it won't always be like that...")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face1#I see. Everyone is a bit uncomfortable with the unfamiliar. Don't worry. It'll get better.")
sm.sendSay("#face0#Illium, you're special.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#I am?")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face1#You are the only one to have overcome your limitations so spectacularly. Believe in yourself and train hard.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#I'll try.")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face2#Now, why don't we begin your #bsupplemental lesson#k?")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Okay!")
sm.sendDelay(1000)
sm.createQuestWithQRValue(34806, "scene=1")
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)
sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)
sm.lockInGameUI(False, True)
