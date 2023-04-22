# id 35904 (Research and Rescue), field 100051010
sm.setSpeakerType(3)
sm.setParam(548)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendNext("#face1#Greetings, fellow...organism. I couldn't help but notice you seem to be in need of some assistance.")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
sm.sendSay("#face1#P-please, you must help me!")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face1#Must I? Hmm... If I do, will you do anything I ask of you in return?")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
sm.sendSay("#face1#Say what?! F-fine... But it has to be something in or near the town. As long as that's okay with you, I'll do whatever you want!")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face1#Okay, you've got yourself a deal. Get ready to be unceremoniously yanked from the ground like a turnip.")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
sm.sendSay("#face1#N-no, wait! You can't just pull me up like some weed! My tender, sensitive body could be torn right in two! Then whatever's inside of me would spill out, and...oh dear, I'm sure it would be a terrible mess.")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face6#O...kay? Then what should I do?")
sm.setSpeakerType(4)
sm.setSpeakerID(1013306) # Mascarpo
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
res = sm.sendAskAccept("#face1#S-seed oil! That's the ticket! I think if you drizzle some of that on me, I'll pop out like a jack-in-the-box...except in reverse, I suppose. Seed oil is pretty common around here, so I don't expect it'll be any trouble to get some.")
sm.setSpeakerType(3)
sm.sendNext("#face1#Bring #b#i4036524# #t4036524##k x#b10#k from #o2300201#s for me!\r\nYou can find Pudgy Flowers nearby in the #m100051011#!")
sm.startQuest(parentID)
