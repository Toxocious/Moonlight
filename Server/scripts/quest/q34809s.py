# id 34809 (A Stranger's Cries), field 402000500
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendNext("#face0#What is that unusual sound?")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face2#Sob, sob... sniff...")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#What...?")
sm.sendSay("#face0#I think someone's crying nearby.")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face0#I hear it too!")
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
sm.sendSay("#face0#I'll guide you toward the noise.")
sm.startQuest(parentID)
