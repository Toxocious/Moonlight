# id 867202402 (Abrup Basin : Training Grounds), field 867202402
sm.showNpcSpecialActionByTemplateId(9400589, "summon", 0)
sm.showNpcSpecialActionByTemplateId(9400592, "summon", 0)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendNext("#face0#You're really something. You've worn us out, and you're not even winded. ")
sm.setInnerOverrideSpeakerTemplateID(9400592) # Aruhi
sm.sendSay("#face1#Phew, no kidding! ")
sm.sendSay("#face0#You're amazing too, Peytour! Makes me think back to that time we were drinking and you got to talking about your past... ")
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendSay("#face0#Hah, you still have the strength to talk? I guess you've still got more left in you! ")
sm.setInnerOverrideSpeakerTemplateID(9400592) # Aruhi
sm.sendSay("#face0#Ack, no! ")
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendSay("#face0#You're going to be training all day today, so get ready! ")
sm.setParam(57)
sm.sendSay("#bYou're going to wear Aruhi out! Take a break. ")
sm.setParam(37)
sm.sendSay("#face0#Hah, I was just joking around. ")
sm.sendSay("#face0#We'll do this again soon, friends. I want the strength to reclaim my village! ")
sm.setInnerOverrideSpeakerTemplateID(9400592) # Aruhi
sm.sendSay("#face0#Yeah! Me too! ")
sm.completeQuestNoCheck(64110)
sm.warp(867202300)
