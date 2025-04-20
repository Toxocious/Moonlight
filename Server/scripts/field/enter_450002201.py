# id 450002201 (Chu Chu Island : Muto's Descent), field 450002201
sm.setMapTaggedObjectVisible("obj_01", True, 0, 0)
sm.createQuestWithQRValue(34220, "a=1;b=1;c=1;e=1;f=1")
sm.lockInGameUI(True, False)
sm.spawnNpc(3003164, 194, 138)
sm.showNpcSpecialActionByTemplateId(3003164, "summon", 0)
sm.spawnNpc(3003162, 143, 138)
sm.showNpcSpecialActionByTemplateId(3003162, "summon", 0)
sm.spawnNpc(3003163, 75, 138)
sm.showNpcSpecialActionByTemplateId(3003163, "summon", 0)
sm.spawnNpc(3003160, -6, 138)
sm.showNpcSpecialActionByTemplateId(3003160, "summon", 0)
sm.blind(True, 255, 0, 0, 0, 0)
sm.removeAdditionalEffect()
sm.setMapTaggedObjectVisible("obj_01", True, 0, 0)
sm.completeQuestNoCheck(34224)
sm.blind(True, 255, 0, 0, 0, 0)
sm.sendDelay(1200)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.sendDelay(1400)
sm.spawnNpc(3003167, -400, 138)
sm.showNpcSpecialActionByTemplateId(3003167, "summon", 0)
sm.moveNpcByTemplateId(3003167, False, 70, 250)
sm.sendDelay(1000)
sm.spawnNpc(3003168, -400, 138)
sm.showNpcSpecialActionByTemplateId(3003168, "summon", 0)
sm.moveNpcByTemplateId(3003168, False, 220, 250)
sm.sendDelay(1000)
sm.setSpeakerType(3)
sm.setParam(57)
sm.setColor(1)
sm.sendNext("#bChief! Chef!#k Hurry, over here!")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3003150) # Lyon
sm.sendSay("(Panting) Well...?! Where is that mysterious #bassistant#k who helped you, and where's that #bdish#k of yours?")
sm.setParam(57)
sm.sendSay("...Seriously? They're standing right there... Ahem. Allow me to introduce, the #bDelicious Beefy Tastesplosion Sandwich#k, and #bSimia#k, the magnificent chef who assisted me! ")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("W-what?! #bSimia#k?")
sm.setInnerOverrideSpeakerTemplateID(3003151) # Simia
sm.sendSay("H-hello Chef...")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("#face0#You! The entire kitchen is in a fervor trying to prepare my masterpiece \r\nfor Muto before it's too late, and you... You were you helping \r\nthat idiot over there make food this whole time?")
sm.setInnerOverrideSpeakerTemplateID(3003151) # Simia
sm.sendSay("I'm sorry... I know the kitchen is very busy... But it's not like you would've let me cook anyway. That's why I wanted to help this traveler...")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("#face0#Even if you cannot cook, there is still trash to empty! Slurp! \r\nThere are many things to do! Don't think for a moment I will forget this.")
sm.setInnerOverrideSpeakerTemplateID(3003153) # Pibik
sm.sendSay("Hey! Who's this long-tongued jerk?")
sm.setInnerOverrideSpeakerTemplateID(3003154) # Pimi
sm.sendSay("It must be that stupid bully Chef. Yawn. I'm bored now...")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("Who are these pipsqueaks?! Oh, you must be the three siblings who \r\nhave #btaste only for garbage#k, just like Simia!")
sm.sendSay("#face4#Even if you were in a hurry, why would you accept help from those \r\nwith no taste making food? Pitiful!")
sm.setInnerOverrideSpeakerTemplateID(3003150) # Lyon
sm.sendSay("#face0#But the dish has such a #bcatchy name#k!")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("#face0#Stupid Chief! There's no time!\r\nHurry and instruct Muto to pick a food!")
sm.setInnerOverrideSpeakerTemplateID(3003150) # Lyon
sm.sendSay("Hold your horses. I'm on it!\r\nHey Muto! #bGulla#k is attacking! We've prepared #b2 types#k of food for you. So eat something and go kick #bGulla#k's butt!")
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendSay("#face2#Mu-to... is... hun... gry...")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("Hey! #bBlockhead#k! Stop saying you're hungry and try my dish!\r\nI don't know why you've been so picky about your food,\r\nbut even you will see my dish is a masterpiece!")
sm.setInnerOverrideSpeakerTemplateID(3003151) # Simia
sm.sendSay("Chef... You shouldn't be so mean to Muto... Muto is a nice child who protects us.")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("#face0#W-what?! Are you lecturing me? You're not even a real chef, and now \r\nyou're trying to tell me what to do?")
sm.sendSay("#face0#Simia! You are hereby banned from my kitchen! \rmPtooey! I am finished with you!")
sm.setInnerOverrideSpeakerTemplateID(3003151) # Simia
sm.sendSay("...")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("Hurry up and choose Muto! Do you know how much trouble you have \r\nput us through?! Hurry up and eat this, and go defeat Gulla!")
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendSay("Hm... (Sniff, sniff)")
sm.setInnerOverrideSpeakerTemplateID(3003150) # Lyon
sm.sendSay("Oh... Muto's giving it the old sniff test!")
sm.flipNpcByTemplateId(3003164, False)
sm.flipNpcByTemplateId(3003162, False)
sm.flipNpcByTemplateId(3003163, False)
sm.flipNpcByTemplateId(3003160, False)
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendSay("#fs50#Hmm?!")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("#face4#Doesn't it smell delectable? Slurp! Now, consume my delicious \r\ndelicacy, and get up!")
sm.onLayer(1500, "eat", 0, 0, 0, "Map/Effect2.img/ArcaneRiver2/eat", 4, True, -1, False)
sm.sendDelay(3000)
sm.offLayer(1500, "eat", False)
sm.sendDelay(2500)
sm.speechBalloon(True, 0, 0, "!", 2500, 1, 0, 0, 0, 4, 0, 4878499)
sm.speechBalloon(True, 0, 0, "!", 2500, 1, 0, 0, 0, 4, 3003160, 4878499)
sm.speechBalloon(True, 0, 0, "!", 2500, 1, 0, 0, 0, 4, 3003162, 4878499)
sm.speechBalloon(True, 0, 0, "!", 2500, 1, 0, 0, 0, 4, 3003163, 4878499)
sm.speechBalloon(True, 0, 0, "!", 2500, 1, 0, 0, 0, 4, 3003164, 4878499)
sm.speechBalloon(True, 0, 0, "!", 2500, 1, 0, 0, 0, 4, 3003167, 4878499)
sm.speechBalloon(True, 0, 0, "!", 2500, 1, 0, 0, 0, 4, 3003168, 4878499)
sm.setInnerOverrideSpeakerTemplateID(3003155) # Pidol
sm.sendNext("M-Muto! Chooses our #bDelicious Beefy Tastesplosion Sandwich#k!")
sm.completeQuestNoCheck(34223)
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendSay("#face0##fs30#Th... This is... Goo... Good!")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("#face0#What are you talking about Muto? You haven't even tried my food!\r\nHurry and eat it!")
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendSay("#face0#Muto... HATE.. You eat it...")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("#face0#You ungrateful rock! Why aren't you eating my food!?")
sm.setParam(57)
sm.sendSay("Shut it, Lyck! Muto doesn't want to eat it!")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3003153) # Pibik
sm.sendSay("Yeah! Your food tastes like #bCrilia poop#k!")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("#face0#How DARE you! I won't accept this insult! Eat! Eat it now!")
sm.completeQuestNoCheck(34225)
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendNext("#face1##fs50#NO EAT!")
sm.setInnerOverrideSpeakerTemplateID(3003150) # Lyon
sm.sendSay("Uh... Is it time for me to step in!? Chef Lyck, go get some air! Hyaaa!")
sm.blind(True, 500, 255, 255, 255, 0)
sm.sendDelay(2000)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.sendNext("There. Master Lyck should be landing safely in the village any minute now.")
sm.setParam(57)
sm.sendSay("H-huh? I have the strangest feeling of Deja vu...")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendSay("#face2##fs30#Waaah! No eat! No fight!")
sm.setInnerOverrideSpeakerTemplateID(3003150) # Lyon
sm.sendSay("Muto, didn't we give you delicious food? Now get up and stop Gulla!")
sm.setInnerOverrideSpeakerTemplateID(3003151) # Simia
sm.sendSay("Chief, why don't you let me try...")
sm.sendSay("Muto. It's been hard protecting us until now, hasn't it?")
sm.sendSay("You couldn't eat anything you wanted... It must have been hard going hungry for so long... You poor thing...")
sm.sendSay("You don't need to fight if you don't want to. I'm just happy that you're eating... And thank you for eating the food I made. I know it isn't very good...")
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendSay("Muto... Want to eat... the rest...")
sm.setInnerOverrideSpeakerTemplateID(3003151) # Simia
sm.sendSay("#face0#Y-You do? Please, go ahead! We made it #bjust for you#k.")
sm.setMapTaggedObjectVisible("obj_01", False, 0, 0)
sm.completeQuestNoCheck(34223)
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendSay("#face0##fs30#Om... Yum! Gooooood.")
sm.setInnerOverrideSpeakerTemplateID(3003153) # Pibik
sm.sendSay("Ah! He even ate Master Lyck's food!")
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(3003154) # Pimi
sm.sendNext("Gulla's almost here! Eek!")
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendSay("#face0#Muto... is full... ...Thank you.")
sm.sendSay("#face0#Now... I go... Play... With #rGulla#k...")
sm.setInnerOverrideSpeakerTemplateID(3003151) # Simia
sm.sendSay("Muto... You're going to protect us?")
sm.setInnerOverrideSpeakerTemplateID(3003156) # Muto
sm.sendSay("#face0#Yes... Muto... Eat delicious food... Protect... #bfriends#k...")
sm.setInnerOverrideSpeakerTemplateID(3003151) # Simia
sm.sendSay("(Sniffs) #bFriends#k... Muto... Thanks...")
sm.blind(True, 255, 0, 0, 0, 1500)
sm.onLayer(1500, "fight", 0, 0, 0, "Map/Effect2.img/ArcaneRiver2/fight", 4, True, -1, False)
sm.sendDelay(3000)
sm.offLayer(1500, "fight", False)
sm.sendDelay(1000)
sm.lockInGameUI(False, True)
sm.warp(450002021)
