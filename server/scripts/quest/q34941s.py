# id 34941 (Separate Ways 1), field 402000402
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001508) # Zippy
sm.sendNext("#face0#Well... there is one thing we could use your help with.")
sm.sendSay("#face2#This crystal draws way too much attention.")
sm.sendSay("#face3#It even drove all those monsters crazy before we escaped.")
sm.setInnerOverrideSpeakerTemplateID(3001509) # Salvo
sm.sendSay("#face3#We did our best to hide it in the garbaaage!\r\nBut it's a terrible place for long-term storaaage!")
sm.setInnerOverrideSpeakerTemplateID(3001510) # Ferret
sm.sendSay("#face0#Yeah, we need some way to camouflage it.")
sm.setSpeakerType(4)
sm.setSpeakerID(3001418) # Zippy
sm.setInnerOverrideSpeakerTemplateID(3001508) # Zippy
res = sm.sendAskAccept("#face2#There's enough scrap iron here that we could make something for it.")
sm.setSpeakerType(3)
sm.sendNext("#face2#Would you track down about #b30#k #i4036350# #b#t4036350##k items from the #o2400306# monsters in #rWaste Treatment Plant 3#k?")
sm.startQuest(parentID)
