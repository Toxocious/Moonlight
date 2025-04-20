# id 35910 (Helping Hand 3), field 100051000
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendNext("#face2#Ooooh, those look like Toxiblossom Leaves. I'll take them, thank you! It might seem counterintuitive to make a purifying elixir out of something laced with poison, but the poison itself is neutralized by the preparation process. Pretty neat, huh?")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face0#(These ingredients are weird, but they don't seem miraculous or anything. Can they really make a legendary medicinal tonic out of this stuff? Maybe the power is in the steps of the recipe itself.)")
sm.sendSay("#face0#I'm curious. How exactly do you mix all these ingredients?")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendSay("#face0#You may have seen a stone mortar and pestle in the forest outside town. Those have been handed down through the line of chiefs for generations, and that's what we use to grind up everything to make the Wunderlixir.")
sm.sendSay("#face2#It looks like all the preparations for the festival are done. Just let me run one last check to make sure I didn't forget anything.")
sm.completeQuestNoCheck(parentID)
sm.startQuest(11620)
