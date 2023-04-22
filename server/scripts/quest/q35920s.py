# id 35920 (Compass Fragment 3), field 100051034
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013350) # Brie
sm.sendNext("#face0#I don't know how strong the blast would be, but there ARE a couple things I've used when making fireworks for the town festivals.")
sm.sendSay("#face0#Let's see, what were those again...? Oh, right. Dry firewood and flint, the classic fire-starting basics. Fortunately, we can find plenty of both around here.")
sm.sendSay("#face0#I'm sure you noticed that the Log-a-Rhythmic Bugs and Sparkinstone Bugs we've seen have been carrying a lot of rocks and a lot of logs.")
sm.setSpeakerType(4)
sm.setSpeakerID(1013314) # Brie
res = sm.sendAskAccept("#face0#What I need you to do is bring me back #b#i4036529# #t4036529##k x#b5#k and #b#i4036530# #t4036530##k x#b5#k.You remember where the Log-a-Rhythmic bugs are, right? You just squished some not too long ago.")
sm.startQuest(parentID)
