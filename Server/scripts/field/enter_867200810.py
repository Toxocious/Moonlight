# id 867200810 (Abrup Basin : River Entrance to Windsleep Forest), field 867200810
sm.setSpeakerType(3)
sm.setParam(57)
sm.setColor(1)
sm.sendNext("#bRight, I'll gather firewood, so you two bring back some Hellfang meat. ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9400590) # Slaka
sm.sendSay("#face0#What? No! Why should I get stuck with the dangerous task? ")
sm.setParam(57)
sm.sendSay("#bFine. Then let's each bring back 20 pieces of Firewood and 20 cuts of Hellfang Meat. ")
sm.createQuestWithQRValue(64037, "chk=1")
