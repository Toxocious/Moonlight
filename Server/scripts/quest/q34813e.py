# id 34813 (For the Verdant Flora), field 402000501
sm.lockInGameUI(True, False)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001306) # Soldier
sm.sendNext("#face0#Oh, perfect. This should be enough.")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face2#Have you finished extracting mytocrystal mana?")
sm.spawnNpc(3001301, -1470, 50)
sm.showNpcSpecialActionByTemplateId(3001301, "summon", 0)
sm.sendDelay(600)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendNext("#face0#Headmistress Agate!")
sm.setInnerOverrideSpeakerTemplateID(3001306) # Soldier
sm.sendSay("#face0#Oh, hello, Lady Agate!")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face1#Hello.")
sm.sendSay("#face0#You're doing a fine job here, gentlemen. Thank you.")
sm.setInnerOverrideSpeakerTemplateID(3001306) # Soldier
sm.sendSay("#face0#Ah, anything for our people!")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Wow, they must have been working really hard.")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face0#We live in peace because of the sacrifice of good people like those you see before you. Then and now...")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Is it true what the soldiers say? Are we really under constant threat of discovery and destruction?")
sm.sendSay("#face0#I can be stronger, more powerful. I'll protect you. I'll protect everyone.")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face0#You needn't worry yourself. If we remain careful and steadfast, there will be no such day.")
sm.sendSay("#face1#But, just in case, take these potions. You never know when you might need one.")
sm.createQuestWithQRValue(parentID, "m=0;item2=1;d2=1")
sm.sendSay("#face2#Shall we begin practice?")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Yes!")
sm.sendSay("#face0##b(If I'm going to protect our home and people, I'll have to train much harder!)#k")
sm.blind(True, 255, 0, 0, 0, 500)
sm.sendDelay(500)
sm.sendDelay(100)
sm.completeQuestNoCheck(parentID)
sm.sendDelay(100)
sm.createQuestWithQRValue(parentID, "m=0;item2=1;exp=1;d2=1")
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/02/21/16/43")
sm.lockInGameUI(False, True)
sm.warp(402000521)
