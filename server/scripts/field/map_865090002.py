# id 865090002 (San Commerci : Daniella Merchant Union Office), field 865090002
sm.lockInGameUI(True, False)
sm.startQuest(17621)
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendNext("Time is money, and my time is worth a million mesos a minute. Now, please make an appointment.")
sm.startQuest(17621)
sm.setInnerOverrideSpeakerTemplateID(9390217) # Tepes
sm.sendSay("Actually, sir, this young Explorer got our stolen goods back.")
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("Excuse me? Our goods were stolen?")
sm.setInnerOverrideSpeakerTemplateID(9390217) # Tepes
sm.sendSay("Er, you see, sir, these cutthroat bandits stole the goods right out of my hands. I fought back and even tore my pants, but it was this young Explorer who saved me. I'll... be on my way now.")
sm.startQuest(17621)
sm.setParam(57)
sm.sendSay("#b(Tepes is a pretty convincing liar.)#k")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("Ah, well, in that case, I thank you for your help, young Explorer.")
sm.setParam(57)
sm.sendSay("Aw, shucks. Tepes is exaggerating... Like, a lot.")
sm.setParam(37)
sm.sendSay("It is a pleasure to meet you. I am #e#bGilberto Daniella#k#n, prime minister of the Commerci Republic and owner of the Daniella Merchant Union.")
sm.setParam(57)
sm.sendSay("The pleasure is mine. My name is #h0# and I am here on vacation.")
sm.setParam(37)
sm.sendSay("Ah, to be young and free again! But why did you want to see me?")
sm.setParam(57)
sm.sendSay("Um... Well... (Okay, I'm going to have to word this carefully.)")
sm.startQuest(17621)
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Father! I'm hooooome!")
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("How many times do I need to remind you to call me 'prime minister' in public, Leon?")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Sorry, pops. I mean, prime minister pops. I'm back from my voyage. Didn't even bruise my knees this time.")
sm.sendSay("Yo! #h0#, my best friend sidekick. You made it!")
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("You two know each other? This young traveler retrieved some stole goods for us... Or defeated some bandits? I'm still not clear on the story.")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Way to go, #e#b#h0##k#n, buddy! First you saved me in Berry, like the excellent sidekick you are, and now you're impressing my daddy!")
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("You exhibit magnificent skills for a mere traveler, #h0#. The union is in your debt.")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Mere traveler? Puh-lease, daddy-o. #e#b#h0##k#n's an ambassador of peace in place of Empress Cygnus AND my sidekick. At first I thought he was from the #bHeaven#k Empire. Pfft, 'traveler.'")
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("#h0# is... what?")
sm.setParam(57)
sm.sendSay("(Yikes!) Er, yeah, I'm TRAVELING here in place of Empress Cygnus, in the interest of peace... ")
sm.setParam(37)
sm.sendSay("Just a moment ago, you clearly said you were a tourist...")
sm.setParam(57)
sm.sendSay("Look, I'm gonna level with you Prime Minister. Commerci hasn't had much in the way of interaction with the rest of Maple World and suddenly you guys are sending ships around the globe. Empress Cygnus is understandably concerned...")
sm.setParam(37)
sm.sendSay("And why, precisely, are you here?")
sm.setParam(57)
sm.sendSay("Like Leon said, I'm here as an ambassador of peace, on behalf of Empress Cygnus.")
sm.setParam(37)
sm.sendSay("An ambassador of peace, you say?")
sm.setParam(57)
sm.sendSay("Yes. Empress Cygnus wants to establish a treaty of peace and mutual cooperation with the Commerci Republic.")
sm.setParam(37)
sm.sendSay("I see. Well then, you've traveled a long way. Please make yourself at home and rest. The Union is still in your debt.")
sm.completeQuestNoCheck(17621)
sm.lockInGameUI(False, True)
sm.warp(865000002)
