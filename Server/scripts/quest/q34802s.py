# id 34802 (Collect Grossular), field 402000526
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(500, 1000, 500, 50, -70)
sm.sendDelay(500)
sm.sendDelay(500)
sm.forcedFlip(True)
sm.spawnNpc(3001300, 602, -140)
sm.showNpcSpecialActionByTemplateId(3001300, "summon", 0)
sm.removeOverlapScreen(1000)
sm.sendDelay(500)
sm.sendDelay(3000)
sm.createFieldTextEffect("#fnﾳﾪﾴﾮﾰ￭ﾵ￱ ExtraBold##fs18#First Class: Understanding the Mytocrystal", 20, 2200, 6, -50, -50, 1, 4, 0, 0, 0)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001302) # Professor Kalsat
sm.sendNext("#face0#Let's begin, class.")
sm.sendSay("#face0#The #bmytocrystals#k of Grandis have been an important resource for our people since the ancient times.")
sm.sendSay("#face0#The Flora became rulers of Grandis due to our superior ability to extract and use mana from the mytocrystals.")
sm.sendSay("#face0#You'll learn more in history class. For now, we'll learn about the various types of mytocrystals that give us power.")
sm.setMapTaggedObjectVisible("c1_appear", True, 0, 0)
sm.playSound("Sound/SoundEff.img/illium/classroom_crystal", 100)
sm.sendDelay(500)
sm.sendDelay(500)
sm.setMapTaggedObjectVisible("c1_loop", True, 0, 0)
sm.setMapTaggedObjectVisible("c1_appear", False, 0, 0)
sm.sendDelay(100)
sm.sendDelay(500)
sm.sendNext("#face0#Can anyone tell me the name of this beautiful green mytocrystal?")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0##fs12#Grossular.")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#Speak up, Sir.")
sm.setInnerOverrideSpeakerTemplateID(3001302) # Professor Kalsat
sm.sendSay("#face0#Dean?")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face3#It's #bgrossular#k!")
sm.setInnerOverrideSpeakerTemplateID(3001302) # Professor Kalsat
sm.sendSay("#face0#What else do you know about it?")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face3#Uh... umm...")
sm.sendSay("#face1#Well...")
sm.setInnerOverrideSpeakerTemplateID(3001302) # Professor Kalsat
sm.sendSay("#face0#Anyone else?")
sm.sendSay("#face0##bGrossular#k is found deep within forested areas. It's used to treat wounds and recover health.")
sm.setMapTaggedObjectVisible("c2_appear", True, 0, 0)
sm.playSound("Sound/SoundEff.img/illium/classroom_crystal", 100)
sm.sendDelay(500)
sm.sendDelay(500)
sm.setMapTaggedObjectVisible("c2_loop", True, 0, 0)
sm.setMapTaggedObjectVisible("c2_appear", False, 0, 0)
sm.sendDelay(100)
sm.sendDelay(500)
sm.sendNext("#face0#What about this beautiful, deep red mytocrystal? Anyone?")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0##fs12#Pyrope.")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face2#Louder, Sir.")
sm.setInnerOverrideSpeakerTemplateID(3001302) # Professor Kalsat
sm.sendSay("#face0#Carnelian?")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face0##bPyrope#k boasts explosive power, but it's more difficult to extract mana from it.")
sm.setInnerOverrideSpeakerTemplateID(3001302) # Professor Kalsat
sm.sendSay("#face0#Very good. Next...")
sm.setMapTaggedObjectVisible("c3_appear", True, 0, 0)
sm.playSound("Sound/SoundEff.img/illium/classroom_crystal", 100)
sm.sendDelay(500)
sm.sendDelay(500)
sm.setMapTaggedObjectVisible("c3_loop", True, 0, 0)
sm.setMapTaggedObjectVisible("c3_appear", False, 0, 0)
sm.sendDelay(100)
sm.sendDelay(500)
sm.sendNext("#face0#What about this one?")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0##fs24##bObsidian!#k")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face2#Whoa!")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#The dark mytocrystal, obsidian, is difficult to find and holds dark magical energy. If you find one, you should take extra care with this dangerous crystal.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face3#Uh, Ex?")
sm.setInnerOverrideSpeakerTemplateID(3001302) # Professor Kalsat
sm.sendSay("#face0#Correct! What a fascinating robot.")
sm.setSpeakerType(4)
sm.setSpeakerID(3001332) # Professor Kalsat
res = sm.sendAskAccept("#face0#Who's ready for some practical application? Spend the remainder of the class gathering #b10 #i4036163# #t4036163# items#k! What do you say?")
sm.startQuest(parentID)
sm.setSpeakerType(3)
sm.sendNext("#face0#You'll find #b#i4036163# #t4036163##k items with #r#o2400400##k monsters. Is everyone ready? I'll teleport all of you at once. Let's see who returns first!")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#I recommend #busing the Maple Guide#k when you return.")
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)
sm.removeOverlapScreen(1000)
sm.lockInGameUI(False, True)
sm.warp(402000512)
