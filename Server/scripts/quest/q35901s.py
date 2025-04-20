# id 35901 (Find Altar Key 2), field 910090302
sm.setSpeakerID(1013305) # Ancient Crate
sm.setParam(548)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
res = sm.sendAskAccept("#face0#It's written in an ancient script. Question is, is it worth trying to decipher it?")
sm.setSpeakerType(3)
sm.sendNext("#face2#It'll take some time, but... Haha, of course it's worth it. Mostly because it would drive me nuts if I didn't. Ah, curiosity, you're the itch I always have to scratch.")
sm.startQuest(parentID)
