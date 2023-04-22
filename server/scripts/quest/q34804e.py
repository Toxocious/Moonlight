# id 34804 (Being Social), field 402000530
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.blind(True, 255, 0, 0, 0, 450)
sm.sendDelay(500)
sm.forcedFlip(True)
sm.sendDelay(500)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.sendDelay(1000)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendNext("#face0#Every person in this room turned you down?")
sm.sendSay("#face1#I don't understand.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face3#I'll have to try harder.")
sm.spawnNpc(3001307, -50, 53)
sm.showNpcSpecialActionByTemplateId(3001307, "summon", 0)
sm.zoomCamera(2000, 1500, 2000, 370, 0)
sm.moveNpcByTemplateId(3001307, False, 300, 150)
sm.sendDelay(2500)
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendNext("#face0#Hey, Morian!")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face0#Hi, Dean! ")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face0#Do you have a partner? ")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face1#Not yet! Want to team up? ")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face1#Nope. I've already got one. ")
sm.sendSay("#face1#I was just asking. See ya! ")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face2#Well, that wasn't very nice.")
sm.sendSay("#face3#I don't think I like his jokes much.")
sm.sendDelay(500)
sm.showFadeTransition(0, 1000, 3000)
sm.completeQuestNoCheck(34858)
sm.spawnNpc(3001310, 502, -105)
sm.showNpcSpecialActionByTemplateId(3001310, "summon", 0)
sm.removeOverlapScreen(1000)
sm.zoomCamera(1000, 1500, 1000, 370, -50)
sm.sendDelay(1500)
sm.sendNext("#face0#Hey, it looks like we're the last.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face5#I think you're right.")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face0#We should team up. What do you say?")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face5#Actually, that would be really great! Thanks!")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face1#You're the new kid, right? I'm #bMorian#k. Welcome to our school!")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face5#Hi! I'm Illium.")
sm.forcedFlip(True)
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#And I am Ex No. 13. Illium created me when he was 11 years old!")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face3#Take it easy, Ex. No need to tell our life story just yet.")
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)
sm.removeOverlapScreen(1000)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.sendDelay(1000)
sm.sendDelay(100)
sm.completeQuestNoCheck(parentID)
sm.sendDelay(100)
sm.createQuestWithQRValue(parentID, "gossip=1;NpcSpeech=30013141/30013152/30013163/30013174/30013185;exp=1")
sm.lockInGameUI(False, True)
