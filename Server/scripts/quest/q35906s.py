# id 35906 (A Legendary Forest), field 100051010
sm.setSpeakerType(3)
sm.setParam(548)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendNext("#face0#Well... All right, I accept. Lead the way.")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
sm.sendSay("#face0#You're...actually interested? I-I mean, of course! Who wouldn't be?")
sm.sendSay("#face0#Just answering a couple questions in return for you saving me doesn't feel like a square deal. Come and party at our festival and I'll make sure you don't go hungry.")
sm.sendSay("#face0#In our town, everyone's the shy sort, so don't feel bad if folks keep their distance at first. They know a good heart when they see one, though, so I'm sure they'll warm up to you quickly.")
sm.sendSay("#face0#I'm sure you'll all be the best of friends before you know it. I'm always right about these things at least 80% of the time.")
sm.setSpeakerType(4)
sm.setSpeakerID(1013306) # Mascarpo
res = sm.sendAskAccept("#face0#Well, what are we standing around here for? Are you ready to head to Karuppa Town?")
sm.setSpeakerType(3)
sm.sendNext("#face0#All righty, just follow me! We'll be in the ol' Karuppa Town in no time.\r\n\r\n #r * You'll be automatically moved to the quest map.#k")
sm.warp(910090306)
