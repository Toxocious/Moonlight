# id 35917 (Fragmented Compass), field 100051030
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendNext("#face0#Phew... Sorry he's such a weirdo, pestering you with all those eccentric requests. He's not a bad Karuppa at heart, really.")
sm.sendSay("#face0#Oh, right. We should hurry and look for those compass parts. From here on, I'll guide you there personally.")
sm.setSpeakerType(4)
sm.setSpeakerID(1013309) # Brie
res = sm.sendAskAccept("#face2#Heehee. You'll be able to find the compass parts in a jiffy if you stick with me. I know this forest like the back of my hand. So, are you ready to set out?")
sm.setSpeakerType(3)
sm.sendNext("#face2#Okie dokie. Just follow me! We're off to the Creepy Crawly Copse to bring back those compass parts!")
sm.createQuestWithQRValue(35948, "00=h0;10=h0;11=h1;02=h1;12=h0;22=h1;13=h0;23=h1;14=h0;15=h0;06=h0;07=h0;16=h0;26=h1;08=h0;17=h0;09=h0;19=h0")
sm.createQuestWithQRValue(35948, "00=h0;10=h0;11=h1;02=h1;12=h1;22=h1;13=h0;23=h1;14=h0;15=h0;06=h0;07=h0;16=h0;26=h1;08=h0;17=h0;09=h0;19=h0")
sm.startQuest(parentID)
