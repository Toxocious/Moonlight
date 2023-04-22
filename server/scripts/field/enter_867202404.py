# id 867202404 (Abrup Basin : Training Grounds), field 867202404
sm.lockInGameUI(True, False)
sm.spawnNpc(9400597, -270, -70)
sm.showNpcSpecialActionByTemplateId(9400597, "summon", 0)
sm.spawnNpc(9400632, -450, -70)
sm.showNpcSpecialActionByTemplateId(9400632, "summon", 0)
sm.spawnNpc(9400633, -520, -70)
sm.showNpcSpecialActionByTemplateId(9400633, "summon", 0)
sm.spawnNpc(9400583, -110, -70)
sm.showNpcSpecialActionByTemplateId(9400583, "summon", 0)
sm.spawnNpc(9400585, -45, -70)
sm.showNpcSpecialActionByTemplateId(9400585, "summon", 0)
sm.setSpeakerType(3)
sm.setParam(57)
sm.setColor(1)
sm.sendNext("#bPhew! Good job everyone.")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Whew... I swear... I used to be an amazing harpooner... back in the day...")
sm.moveNpcByTemplateId(9400583, True, 50, 50)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendNext("#face0#Wow! You're much better than I was expecting! ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Hmm, really? ")
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendSay("#face0#Yep! I mean, you're still a little lacking on power... ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Hmmm... Really? ")
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendSay("#face0#Your attacks were very quick and precise. Maybe because you've been hunting small fish. ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Really?! ")
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendSay("#face0#You know, I was thinking it would help if we could net the monsters when they attack... ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#A net?! ")
sm.sendDelay(500)
sm.sendDelay(1000)
sm.speechBalloon(False, 0, 0, "How?", 2000, 1, 0, 0, 0, 4, 9400632, 4878499)
sm.sendDelay(3000)
sm.speechBalloon(False, 0, 0, "Really?", 2000, 1, 0, 0, 0, 4, 9400633, 4878499)
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendNext("#face0##h0#! Could you spar with us one more time? ")
sm.setParam(57)
sm.sendSay("#bOf course! ")
sm.setParam(37)
sm.sendSay("#face0#I need three people from Afinas to help me. ")
sm.setInnerOverrideSpeakerTemplateID(9400585) # Afinas Soldier
sm.sendSay("Got it! ")
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendSay("#face0#Great. Alright, harpooners... Throw your nets at #h0# and the soldiers on my signal! ")
sm.setInnerOverrideSpeakerTemplateID(9400597) # Gurnardson
sm.sendSay("#face0#Okay, piece of cake! ")
sm.sendDelay(500)
sm.sendDelay(2000)
sm.speechBalloon(False, 0, 0, "Okay!", 2000, 1, 0, 0, 0, 4, 9400632, 4878499)
sm.sendDelay(2000)
sm.speechBalloon(False, 0, 0, "Got it!", 2000, 1, 0, 0, 0, 4, 9400633, 4878499)
sm.setInnerOverrideSpeakerTemplateID(9400583) # Gillie
sm.sendNext("#face0#I'll be attacking as well! Watch out, #h0#! ")
sm.completeQuestNoCheck(64119)
sm.startQuest(64120)
sm.lockInGameUI(False, True)
sm.warp(867202460)
