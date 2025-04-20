# id 35908 (Helping Hand 1), field 100051000
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
sm.sendNext("#face0#Like the chief said, everyone's busy with festival-related business. But who knows? If you give 'em a hand with their duties, we might be able to get everything ready faster.")
sm.setSpeakerType(4)
sm.setSpeakerID(1013302) # Mascarpo
res = sm.sendAskAccept("#face0#What do you say, Explorer? Are you up for helping out with some work around town? Nothing endears an outsider to us Karuppa quite as fast as reducing the amount of work we have to do ourselves.")
sm.setSpeakerType(3)
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendNext("#face0#Yeah, I can do that. The only question is, who should I help first?")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
sm.sendSay("#face0#We've only known each other a few hours and you're already such a team player! Don't you worry about a thing - I'll send you where your help is most needed.")
sm.sendSay("#face0#There's a youngster named Brie who's in charge of preparing all the ingredients for the Wunderlixir. It's one of the festival's most challenging jobs, so I think she'd definitely appreciate a hand with the work.")
sm.sendSay("#face0#Brie is a pretty upbeat kid - a real ray of sunshine compared to most of us in this village, so there's no need to worry about her giving you the cold shoulder. Anyway, good luck. We're counting on you!")
sm.startQuest(parentID)
