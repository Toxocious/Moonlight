# id 34807 (Dean's Lost Stuff), field 402000532
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
sm.sendNext("#face2#Everyone else took off and left me here alone.")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Well, I'm glad you're safe.")
sm.setSpeakerType(4)
sm.setSpeakerID(3001337) # Dean
sm.setInnerOverrideSpeakerTemplateID(3001307) # Dean
res = sm.sendAskAccept("#face2#I know it's asking a lot, but since you'll be eliminating all those broken robots anyway, would you collect my parts for me...?")
sm.startQuest(parentID)
sm.setSpeakerType(3)
sm.sendNext("#face1#Thank you so much! You'll find them through the#b left portal#k!")
sm.sendSay("#face1#If you eliminate #r#o2400409##k monsters, you'll find #i4036165# #bmy bag#k and #i4036166# #bmy book x10#k eventually! Thanks a lot!")
