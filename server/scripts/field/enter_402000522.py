# id 402000522 (null), field 402000522
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.spawnNpc(3001300, -491, -1377)
sm.showNpcSpecialActionByTemplateId(3001300, "summon", 0)
sm.flipNpcByTemplateId(3001300, True)
sm.sendDelay(30)
sm.forcedFlip(True)
sm.sendDelay(30)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendNext("#face0#I'm going to take a moment to think.")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#Class is starting.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face4#I just need to figure out the best way to talk to people.")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#It's just a matter of confidence, Sir. With a couple of positive interactions, you'll have friends in no time!")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Yeah. I shouldn't let my undeveloped skills hold me back from learning and improving.")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#Every student at the Crystal Academy has an innate talent for using mytocrystal mana, but there's no reason you can't one day reach their skill level.")
sm.sendSay("#face0#You can do it, Sir.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Yeah, it'll be a lot of hard work, but it's worth it.")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#You should probably start your class.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face4#You're right.")
sm.sendSay("#face0#Here goes.")
sm.blind(True, 255, 0, 0, 0, 500)
sm.sendDelay(500)
sm.blind(False, 0, 0, 0, 0, 500)
sm.lockInGameUI(False, True)
sm.createQuestWithQRValue(34802, "out=2")
