# id 35914 (Ancient Compass), field 100051030
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013351) # Gorgonz
sm.sendNext("#face0#See, there's this compass I use whenever I'm searching for something. If you place an object connected to the thing you're looking for near it, it'll tell you the direction you need to go to reach it.")
sm.setSpeakerType(4)
sm.setSpeakerID(1013310) # Gorgonz
res = sm.sendAskAccept("#face0#Curious? Just follow me if you wanna see. It's set up out back. Not exactly a long walk.")
sm.setSpeakerType(3)
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendNext("#face0#(That sure sounds convenient. I wonder where in the ruins it might lead me if I placed the relic on it...)")
sm.startQuest(parentID)
sm.createQuestWithQRValue(35948, "00=h0;10=h1;11=h0;02=h1;12=h0;22=h1;13=h0;23=h1;14=h0;15=h0;06=h0;07=h0;16=h0;26=h1;08=h0;17=h0;09=h0;19=h0")
sm.createQuestWithQRValue(35948, "00=h0;10=h0;11=h0;02=h1;12=h0;22=h1;13=h0;23=h1;14=h0;15=h0;06=h0;07=h0;16=h0;26=h1;08=h0;17=h0;09=h0;19=h0")
