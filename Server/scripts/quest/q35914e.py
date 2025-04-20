# id 35914 (Ancient Compass), field 100051030
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013351) # Gorgonz
sm.sendNext("#face0#Aw crap. I totally forgot. Because of those loud noises coming from the ruins recently, the compass stopped working. The vibrations just shook a whole mess of pieces right off it.")
sm.sendSay("#face0#It's too bad. That compass was pretty handy at locating things. But not to worry - this has happened before and I've managed to get it in good working order again. We'll just have to find the missing parts and plug them back into the compass.")
sm.sendSay("#face0#I've been...kinda busy lately, so I haven't managed to find them all yet. You'll need to get the last couple if you wanna use the compass. *cough*.")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face0#*sigh* Fine. So where are these missing parts, then?")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013351) # Gorgonz
sm.sendSay("#face0#W-well...")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(35948, "00=h0;10=h0;11=h1;02=h1;12=h0;22=h1;13=h0;23=h1;14=h0;15=h0;06=h0;07=h0;16=h0;26=h1;08=h0;17=h0;09=h0;19=h0")
