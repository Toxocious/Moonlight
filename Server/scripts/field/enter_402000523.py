# id 402000523 (null), field 402000523
sm.createQuestWithQRValue(34804, "gossip=1")
sm.createQuestWithQRValue(34858, "gate=1;cinna=2")
sm.createQuestWithQRValue(34859, "dean=2;extra1=1;extra2=1;carn=3")
sm.createQuestWithQRValue(34859, "dean=2;extra1=2;extra2=1;carn=3")
sm.createQuestWithQRValue(34859, "dean=2;extra1=2;extra2=2;carn=3")
sm.createQuestWithQRValue(34859, "dean=2;extra1=2;extra2=2;carn=4")
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.blind(True, 255, 0, 0, 0, 0)
sm.spawnNpc(3001300, 150, -1381)
sm.showNpcSpecialActionByTemplateId(3001300, "summon", 0)
sm.forcedFlip(True)
sm.sendDelay(300)
sm.blind(False, 0, 0, 0, 0, 700)
sm.sendDelay(700)
sm.forcedMove(False, 100)
sm.moveNpcByTemplateId(3001300, False, 70, 100)
sm.sendDelay(1000)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendNext("#face0#What happened?")
sm.setInnerOverrideSpeakerTemplateID(3001311) # Sinaria
sm.sendSay("#face0#What?")
sm.spawnNpc(3001307, -278, -1381)
sm.showNpcSpecialActionByTemplateId(3001307, "summon", 0)
sm.spawnNpc(3001311, -339, -1381)
sm.showNpcSpecialActionByTemplateId(3001311, "summon", 0)
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face0#It talks! It shouted #fs22##rObsidian#k!")
sm.sendSay("#face1#That was just about the last thing I expected to happen in class today.")
sm.setInnerOverrideSpeakerTemplateID(3001311) # Sinaria
sm.sendSay("#face1#Hey! There goes that student now.")
sm.forcedFlip(True)
sm.flipNpcByTemplateId(3001300, True)
sm.sendDelay(30)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendNext("#face0#What...?")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face0#Hi!")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face4#H-hi!")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face1#Cool robot! Did you make it?")
sm.sendSay("#face0#And how did you get into this school?")
sm.sendSay("#face0#And why doesn't the mytocrystal on your hand have any color?")
sm.sendSay("#face0#Why don't your wings shine like ours?")
sm.sendSay("#face0#Is this robot your pet?")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face4#Huh? What?")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face0#Take it easy, Dean!")
sm.spawnNpc(3001308, -100, -1381)
sm.showNpcSpecialActionByTemplateId(3001308, "summon", 0)
sm.sendSay("#face0#You're overwhelming him.")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face2#What? But I'm just curious!")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face0#He's new. Maybe slow it down and give him a chance to answer first?")
sm.setInnerOverrideSpeakerTemplateID(3001311) # Sinaria
sm.sendSay("#face4#He clearly doesn't want to talk to us. I'm leaving.")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face2#No, wait! It's fun!")
sm.setInnerOverrideSpeakerTemplateID(3001311) # Sinaria
sm.sendSay("#face3#No. We should leave him alone.")
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendSay("#face1#Fine. I'll go. But I'm determined to talk to him eventually.")
sm.sendDelay(800)
sm.sendDelay(500)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendNext("#face3#I'm happy to meet you all. I promise. I'm just a little nervous.")
sm.flipNpcByTemplateId(3001308, False)
sm.sendDelay(30)
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendNext("#face1#Oh, don't mind them. Dean's just a little too enthusiastic sometimes.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Ah, I see.")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face0#What's your name?")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Illium.")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face1#Pleased to meet you! I'm #bCarnelian#k.")
sm.sendSay("#face0#I'd love to stay and chat, but I'm going to be late. See you later!")
sm.sendDelay(800)
sm.sendDelay(500)
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendNext("#face0#I'll guide you to your next class.")
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)
sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)
sm.lockInGameUI(False, True)
