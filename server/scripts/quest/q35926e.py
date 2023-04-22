# id 35926 (Glowpod), field 100051043
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendNext("#face0#Oh, you're back already. What are those things you're holding? Aren't those from the plants that were growing on top of the Ruins Sentinels?")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face0#They have a soft glow normally, but they flare up if you give them a good bop, like this.")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendSay("#face0#Huh. You know, that just might work, even underwater. But we need to be sure, so how about dropping one in the water as a test?")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face3##b(You picked up a glowpod and dropped it into the pool. The soft light illuminated the water for a good while before finally dimming and growing dark.)#k")
sm.sendSay("#face0#(These don't last long enough that I can reach the bottom with just one. I'll have to take all I've gathered and hope for the best.)")
sm.completeQuestNoCheck(parentID)
