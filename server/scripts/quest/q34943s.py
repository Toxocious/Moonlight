# id 34943 (Contact Caravan), field 402000402
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001510) # Ferret
sm.sendNext("#face0#Now that you've helped us again, we wondered what the caravan could do for you.")
sm.sendSay("#face0#You're our friend after all. It's only fair we return the favor.")
sm.setInnerOverrideSpeakerTemplateID(3001500) # Ark
sm.sendSay("#face0#Friend...")
sm.setInnerOverrideSpeakerTemplateID(3001510) # Ferret
sm.sendSay("#face2#So, we made something for you. Here you go.")
sm.sendSay("#face2#This is so exciting! I really hope you like it!")
sm.startQuest(parentID)
