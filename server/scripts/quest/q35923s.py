# id 35923 (Compass Directions), field 100051030
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendNext("#face0#Ehehe... Having said that, I'd be lying if I said I hadn't thought about bailing too. I've never dared to get so close to the ruins before.")
sm.setSpeakerType(4)
sm.setSpeakerID(1013309) # Brie
res = sm.sendAskAccept("#face0#Still... I can't just give up after coming this far. I decided I was gonna do something about this problem, and I mean to see it through.")
sm.setSpeakerType(3)
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendNext("#face6#You do know that if you end up slowing me down, I'm just gonna to leave you behind, right?")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendSay("#face0#And I'm going to do my best to make sure that never happens, because I need to find out what's causing that terrible noise and save our village!")
sm.startQuest(parentID)
sm.createQuestWithQRValue(35948, "00=h0;10=h0;11=h1;02=h1;21=h0;12=h0;22=h1;13=h0;23=h1;14=h0;24=h0;15=h0;06=h0;25=h0;07=h0;16=h0;26=h1;08=h0;17=h0;09=h0;19=h0")
sm.createQuestWithQRValue(35948, "00=h0;10=h0;11=h1;02=h1;21=h0;12=h0;22=h1;13=h0;23=h1;14=h0;24=h0;15=h0;06=h0;25=h0;07=h0;16=h0;26=h1;08=h0;17=h0;09=h0;19=h1")
