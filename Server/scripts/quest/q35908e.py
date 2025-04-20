# id 35908 (Helping Hand 1), field 100051000
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendNext("#face0#Heehee. You're the brave Explorer who pulled Mascarpo out of the ground when he got stuck, right? I maaaaay have been eavesdropping on your conversation with Chief Gooda. In my defense, we don't get a lot of visitors!")
sm.sendSay("#face2#Hmm... You do sort of give off an unhappy vibe like the chief said, but I don't think you're a bad person.")
sm.sendSay("#face1#I... I really admire people who have courage like that. They always seem like they can do anything. I wish I could be more like them...")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face0##b(Her upbeat disposition suddenly dims, a pained look on her face. But just as quickly as she had grown downcast, she rallies her cheer, as though to snap her out of her funk.)#k")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendSay("#face0#O-oh! Sorry, I totally spaced and forgot to introduce myself. I'm Brie! I'm in charge of the festival preparations this year.")
sm.sendSay("#face0#It's a lot of work preparing for a festival held to wish for the town's well being. Lots of pressure. It'd be easier if that loud noise would just give it a rest this year.")
sm.sendSay("#face0#Ack, silly me, getting all carried away! You came to help me with my festival work, right? Well, before I babble your ear off, let me tell you what ingredients we still need.")
sm.completeQuestNoCheck(parentID)
sm.startQuest(11620)
