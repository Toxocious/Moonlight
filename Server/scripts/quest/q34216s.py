# id 34216 ([Chu Chu] Gulla Attacks), field 450002000
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3003150) # Lyon
sm.sendNext("It's #rGulla#k! #rGulla has begun his assault#k!")
sm.sendSay("#bMaster Lyck#k! Have you completed your meal for Muto?")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("Of course! Just you watch, Muto will be #bjumping for joy#k because \r\nit's so good!")
sm.setInnerOverrideSpeakerTemplateID(3003150) # Lyon
sm.sendSay("Oh! What a relief! But what happened to that #bstrange little traveler#k who went off to make their own dish?")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("#face4#Slurp-slurp! Surely off cowering in fear! Hmph. \r\nThat runt doesn't know a thing about taste, and they dared to \r\nlecture ME about flavor! Well, now their true colors are showing!")
sm.setInnerOverrideSpeakerTemplateID(3003150) # Lyon
sm.sendSay("Um. Anyways Master Lyck, we should take your masterpiece to Muto!")
sm.setParam(57)
sm.sendSay("W-wait! I'm here! (Huffs) The food... It's ready!")
sm.setParam(37)
sm.sendSay("#face1#Oh! You're back!")
sm.setParam(57)
sm.sendSay("(Pants) Yes! Let's go to Muto...")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("What? Where is this food you speak of? Don't tell me you made \r\nsomething small... Your last offering was smaller than one of \r\nMuto's boogers!")
sm.setParam(57)
sm.sendSay("(Breathes heavily) I prepared #ban amazing dish#k... and I had #bhelp from an excellent chef#k...")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(3003150) # Lyon
sm.sendSay("Oh! An #bassistant#k? Well that's nice. Who are they?")
sm.setInnerOverrideSpeakerTemplateID(3003152) # Master Lyck
sm.sendSay("#face4#Slurp-slurp! Liar! There's no one on Chu Chu Island that \r\ncooks half as well as me!")
sm.setParam(57)
sm.sendSay("Hey, uh, aren't we a little short on time here? We should get to Muto! \r\nMy #bassistant#k is already bringing our dish there!")
sm.startQuest(parentID)
sm.completeQuestNoCheck(parentID)
sm.warp(450002021)
