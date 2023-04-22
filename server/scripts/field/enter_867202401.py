# id 867202401 (Abrup Basin : Training Grounds), field 867202401
sm.showNpcSpecialActionByTemplateId(9400589, "summon", 0)
sm.showNpcSpecialActionByTemplateId(9400592, "summon", 0)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendNext("#face0#So, how shall we begin? ")
sm.setParam(57)
sm.sendSay("#bSparring! Just imagine that I'm a monster, and attack. ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400592) # Aruhi
sm.sendSay("#face0#A-attack you? ")
sm.setParam(57)
sm.sendSay("#bYes, both of you attack at once! ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendSay("#face0#Hah, you're full of confidence! Are you sure? ")
sm.setParam(57)
sm.sendSay("#bEasy peasy! ")
sm.setParam(37)
sm.sendSay("#face0#Ha ha! Aruhi, let's show #h0# what we can do! ")
sm.sendSay("#face0#Let's GO!!")
sm.warp(867202420)
