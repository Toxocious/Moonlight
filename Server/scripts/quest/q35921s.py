# id 35921 (The Wait for Daybreak), field 100051030
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013351) # Gorgonz
sm.sendNext("#face0#Eeeey, look who's back. I figured it'd be too difficult to get those compass parts, so-- Wait, what? You really managed to get them all?! I'm speechless! ")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face0#Yes, I think I'd appreciate some speechlessness as you get to work on fixing that compass without delay.")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013351) # Gorgonz
sm.sendSay("#face0#Yeah, yeah, don't nag me. Even when I fix it, the compass needs to be exposed to sunlight to work, so it's not like we'll get immediate results.")
sm.sendSay("#face0#We've got plenty of time until noon rolls around, so I just need to have it fixed up by then. Gah, more work.")
sm.setSpeakerType(4)
sm.setSpeakerID(1013311) # Gorgonz
res = sm.sendAskAccept("#face0#Anyway, just give me those pieces and I'll get this thing back in working order. As for you two, get some rest. I'll call you when I'm done.")
sm.startQuest(parentID)
sm.setSpeakerType(3)
sm.sendNext("#face0#Gotta put that compass back to-geeee-ther!\r\n'Cause we can't miss that sunny weaaaa-ther!")
