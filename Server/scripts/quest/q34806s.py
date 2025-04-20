# id 34806 (Special Activities), field 402000528
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendNext("#face1#Hello, Illium! How was your class?")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Headmistress Agate?!")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face2#Surprised? We can communicate through the lights like this. Isn't that convenient?")
sm.sendSay("#face0#I hear you had a rough start in your magical combat class today.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Well...")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face0#Don't be discouraged. No one masters it in one day.")
sm.sendSay("#face1#If you'd like to take private tutoring lessons after school, you may be able to catch up to the rest of your class.")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Tutoring already?")
sm.setInnerOverrideSpeakerTemplateID(3001301) # Agate
sm.sendSay("#face1#Yes. Once you've finished class for the day, report to my office.")
sm.createQuestWithQRValue(parentID, "scene=1;item1=1")
sm.startQuest(parentID)
sm.sendSay("#face2#Those potions should get you through the rest of the day. Good luck!")
sm.setInnerOverrideSpeakerTemplateID(3001350) # Illium
sm.sendSay("#face0#Um... thanks, I think.")
sm.sendSay("#face0#Wow... Magic lessons from Headmistress Agate herself!")
sm.sendSay("#face0#How about that, Ex? I'll be an expert in no time!")
