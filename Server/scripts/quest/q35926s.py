# id 35926 (Glowpod), field 100051043
sm.setSpeakerType(3)
sm.setParam(548)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendNext("#face0#(Those Ruins Sentinels we passed on the way here had some sort of luminous plant growing on their heads. If I got enough of those bulbs, I might be able...)")
sm.setSpeakerType(4)
sm.setSpeakerID(1013317) # Brie
res = sm.sendAskAccept("#face3#(Okay. We'll have to double back a bit to #m100051042#. I'll grab 10 of those #i4036531# #t4036531# from the #o2300208# monsters and we'll see how much light they give off when bunched together.)")
sm.setSpeakerType(3)
sm.sendNext("#face0#There's something I want to check out. Wait for me here.")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendSay("#face0#Of course. It looks like you've already come up with a solution, just like I knew you would. I'll be waiting, then.")
sm.startQuest(parentID)
