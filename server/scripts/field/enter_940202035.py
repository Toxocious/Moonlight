# id 940202035 (null), field 940202035
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.blind(True, 255, 0, 0, 0, 0)
sm.sendDelay(300)
sm.zoomCamera(0, 1000, 0, -1450, 65)
sm.sendDelay(300)
sm.spawnNpc(3001346, -2800, 30)
sm.showNpcSpecialActionByTemplateId(3001346, "summon", 0)
sm.spawnNpc(3001310, -1370, 30)
sm.showNpcSpecialActionByTemplateId(3001310, "summon", 0)
sm.spawnNpc(3001311, -1300, 30)
sm.showNpcSpecialActionByTemplateId(3001311, "summon", 0)
sm.spawnNpc(3001316, -1900, 50)
sm.showNpcSpecialActionByTemplateId(3001316, "summon", 0)
sm.spawnNpc(3001315, -1965, 50)
sm.showNpcSpecialActionByTemplateId(3001315, "summon", 0)
sm.spawnNpc(3001317, -2030, 50)
sm.showNpcSpecialActionByTemplateId(3001317, "summon", 0)
sm.spawnNpc(3001318, -2095, 50)
sm.showNpcSpecialActionByTemplateId(3001318, "summon", 0)
sm.spawnNpc(3001319, -2160, 50)
sm.showNpcSpecialActionByTemplateId(3001319, "summon", 0)
sm.spawnNpc(3001308, -1220, 30)
sm.showNpcSpecialActionByTemplateId(3001308, "summon", 0)
sm.spawnNpc(3001307, -1150, 30)
sm.showNpcSpecialActionByTemplateId(3001307, "summon", 0)
sm.completeQuestNoCheck(34900)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendNext("#face4#This way! Hurry!")
sm.forcedMove(True, 150)
sm.moveNpcByTemplateId(3001310, True, 150, 150)
sm.moveNpcByTemplateId(3001311, True, 150, 150)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendNext("#face2#Morian! Sinaria!")
sm.sendDelay(500)
sm.zoomCamera(500, 1000, 500, -1350, 65)
sm.sendDelay(500)
sm.forcedFlip(True)
sm.flipNpcByTemplateId(3001310, False)
sm.flipNpcByTemplateId(3001311, False)
sm.sendDelay(30)
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendNext("#face3#Hey!")
sm.createQuestWithQRValue(49000, "count=0;day=152722;QET=20190221174556;state=1")
sm.moveNpcByTemplateId(3001308, True, 150, 150)
sm.moveNpcByTemplateId(3001307, True, 150, 150)
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face2#You're all okay!")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face4#Dean! Carnelian!")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face2#Who are you? Wait, is that... Illium?!")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face4#I'll explain later. We need to keep moving.")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face3#I'm so scared.")
sm.setInnerOverrideSpeakerTemplateID(3001311) # Sinaria
sm.sendSay("#face4#Keep it together! We'll make it.")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face4#All of you! Quickly!")
sm.setInnerOverrideSpeakerTemplateID(3001362) # Quiet Student
sm.sendSay("You made it!")
sm.sendDelay(500)
sm.forcedFlip(True)
sm.flipNpcByTemplateId(3001310, True)
sm.flipNpcByTemplateId(3001311, True)
sm.sendDelay(30)
sm.zoomCamera(1500, 1000, 1500, -1850, 65)
sm.forcedMove(True, 150)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(3001363) # Moody Student
sm.sendNext("What a relief!")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face0#Everyone is safe!")
sm.setInnerOverrideSpeakerTemplateID(3001361) # Arrogant Student
sm.sendSay("But... what about Headmistress Agate?!")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face3#She... didn't make it...")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face2#What?! How can that be?")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face2#This doesn't make any sense!")
sm.setInnerOverrideSpeakerTemplateID(3001311) # Sinaria
sm.sendSay("#face4#What can we do?")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face3#Survive.")
sm.sendSay("#face3#Agate died to save us all. We have to make it.")
sm.sendSay("#face0#There! The Crystal Gate!")
sm.zoomCamera(900, 1500, 900, -2800, 30)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendNext("#face2#Something about it doesn't look right.")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face3#If we can get through that gate, we'll be safe. Go!")
sm.blind(True, 255, 0, 0, 0, 500)
sm.sendDelay(500)
sm.moveCamera(True, 0, 0, 0)
sm.startQuest(34819)
sm.sendDelay(100)
sm.completeQuestNoCheck(34819)
sm.sendDelay(100)
sm.createQuestWithQRValue(34819, "exp=1")
sm.lockInGameUI(False, True)
sm.warp(940202033)
