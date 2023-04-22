# id 34820 (One Look Back), field 940202040
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001326) # Hoodlum
sm.sendNext("#face0#Yikes. The white-haired one is stronger than I expected.")
sm.setParam(37)
sm.sendSay("#face0#What are you!?")
sm.setInnerOverrideSpeakerTemplateID(3001311) # Sinaria
sm.sendSay("#face1#That's not your concern.")
sm.setInnerOverrideSpeakerTemplateID(3001308) # Carnelian
sm.sendSay("#face2#And we're not the High Flora either!")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face4#Tell us what you know about the other dimension!")
sm.setInnerOverrideSpeakerTemplateID(3001326) # Hoodlum
sm.sendSay("#face0#'Other dimension'?")
sm.sendSay("#face0#What is that?")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face0#You'd better not be lying to us!!")
sm.setInnerOverrideSpeakerTemplateID(3001326) # Hoodlum
sm.sendSay("#face0#We don't know anything! Honest!")
sm.sendSay("#face0#The Nova might know!")
sm.sendSay("#face0#Yes! The Nova! In Pantheon! Try there!")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face4#The Nova...? I've only ever read about them. You mean the dragons?")
sm.setInnerOverrideSpeakerTemplateID(3001326) # Hoodlum
sm.sendSay("#face0#Yes! That's all we know! Please!")
sm.setInnerOverrideSpeakerTemplateID(3001353) # Illium
sm.sendSay("#face0#I guess our first stop will be Pantheon. Let's go guys.")
sm.setInnerOverrideSpeakerTemplateID(3001310) # Morian
sm.sendSay("#face0#Well now that's sorted out, do you guys have any food?")
sm.setInnerOverrideSpeakerTemplateID(3001311) # Sinaria
sm.sendSay("#face3#Morian!")
sm.setSpeakerType(4)
sm.setSpeakerID(3001343) # Hoodlum
sm.setInnerOverrideSpeakerTemplateID(3001300) # Ex
res = sm.sendAskAccept("#face0#Starting navigation to Pantheon.")
sm.startQuest(parentID)
sm.warp(402000112)
