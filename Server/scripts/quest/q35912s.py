# id 35912 (Hero, Hero!), field 100051020
sm.setSpeakerType(3)
sm.setParam(548)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendNext("#face6#I thought everyone ran away. What are you still doing here?")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendSay("#face0#I was hiding and I saw that thing you pulled out to show the chief. Does that relic have some connection to the ruins outside of town?")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face0#And what if it does?")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendSay("#face1#We Karuppa are scared of those ruins, and the sound that sometimes comes from them. It's a weird sound...like loud crash and a yawning drone you can hear clearly even in town.")
sm.sendSay("#face1#It feels like it gets a bit louder every time, and more ominous, but all we can do is carry on and put all our hearts into our festivals.")
sm.sendSay("#face1#No one's ever ventured INSIDE the ruins to find out what's going on in there or what causes the noise. They're scared...and so am I.")
sm.sendSay("#face1#Eeeep! Just thinking about it is giving me the heebie-jeebies! Still, we can't just let things go on like this. I feel like it's only a matter of time before it turns from just scary to really dangerous.")
sm.sendSay("#face1#Someone's gotta step up and DO something. We're too caught up in our own fear. But not you. You're brave, and strong, too.")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face0#I have a feeling I know where this is going, but...just tell me what you're getting at.")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendSay("#face1#Okay, so... What I'm trying to say is, if you wouldn't mind a scaredy-cat like me, could you maybe...")
sm.sendSay("#face1#...take me along as you investigate your relic?")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face0#You? What sort of help would you be?")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendSay("#face0#W-well, I'm good at...helper-y stuff. I can't fight or anything, but if you need anything from in or around the town, you can count on me.")
sm.sendSay("#face0#Plus, there's someone I can introduce you to. He's kind of a weirdo, but he's smart!")
sm.sendSay("#face0#His name is Gorgonz. He's a kooky guy who hangs around outside town and observes the ruins. He collects weird odds and ends too, but I don't know what for.")
sm.setSpeakerType(4)
sm.setSpeakerID(1013307) # Brie
res = sm.sendAskAccept("#face0#If we find him, he might be able to answer a few of your questions. Of course, I'll handle the introductions.")
sm.setSpeakerType(3)
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendNext("#face0#(I never thought of myself as someone who'd do the whole hero/sidekick thing, but... If this can net me a few good leads, then sure, I'll play along.)")
sm.sendSay("#face6#Moving in a group makes it harder to avoid detection. But once the investigation's done, you're not tagging along after that. Got it?")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendSay("#face2#So you're saying you WILL take me then, right? I just know we'll make a great team!")
sm.completeQuestNoCheck(parentID)
sm.startQuest(11620)
sm.createQuestWithQRValue(15710, "lasttime=19/07/08/13/37")
