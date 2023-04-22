# id 1 (CaravanP1_chk1), field 867200500
sm.createQuestWithQRValue(64006, "WC=0;speed=20;man=200;prog=0;Pt=Caravan_chk1;Ec=1;weather=0;max=16;food=450")
sm.createQuestWithQRValue(64006, "WC=0;speed=20;man=200;prog=0;Pt=CaravanP1_chk1;Ec=1;weather=0;max=16;food=450")
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400581) # Butler
sm.sendNext("#face0#Chief. Since our knights are protecting the caravan, I will assume command here. ")
sm.sendSay("#face0#I will need you to provide directions to our destination, of course. ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#No. My villagers form the majority of this group, so I will remain in charge. ")
sm.sendSay("#face0#Since you are unfamiliar with the land and the weather, Captain, it would be better if you focused on matters of defense. ")
sm.setInnerOverrideSpeakerTemplateID(9400581) # Butler
sm.sendSay("#face0#Chief, have you ever led this many people? ")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#Captain, have you ever lead your dispatch through a snowstorm like this?")
sm.setInnerOverrideSpeakerTemplateID(9400581) # Butler
sm.sendNext("#face0#The snowstorm is closing in. We have no time to waste.")
sm.sendSay("#face0#We should increase our pace, and leave the food to delay the monsters.")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#Are you mad? You want to throw away the food we worked so hard to gather?!")
sm.sendSay("#face0#We'll continue as we have, and send the knights and hunters to protect the caravan.")
sm.setInnerOverrideSpeakerTemplateID(9400581) # Butler
sm.sendSay("#face0#Weren't you just talking about how dangerous the snowstorm is?")
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendSay("#face0#Kan... Vice Captain Butler... Please compose yourselves. If we're to survive this journey, we must work together without so much infighting.")
sm.setInnerOverrideSpeakerTemplateID(9400587) # Kan
sm.sendSay("#face0#If we give up our food, it will slow our people as hunger sets in.")
sm.setInnerOverrideSpeakerTemplateID(9400581) # Butler
sm.sendSay("#face0#Giving up the food will delay the monsters and give us more room to maneuver.")
sm.setInnerOverrideSpeakerTemplateID(9400589) # Peytour
sm.sendNext("#face0##h0#, it might be better not to take a side in this situation. ")
sm.sendSay("#face0#I hate to put this on you, but can you help us out? ")
sm.sendSay("#face0#I will leave our fate in your capable hands.")
sm.setParam(35)
sm.sendSay("Go to #m867200600#.")
sm.warp(867200600)
