# id 35905 (Investigation), field 100051010
sm.setSpeakerID(1013306) # Mascarpo
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
res = sm.sendAskAccept("#face1#Phew... I'm a bit dazed, as you can probably see, but a deal is a deal. What it is you want me to do for you? Go ooon, don't be shy now.")
sm.setSpeakerType(3)
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendNext("#face0#I don't want much. Information, mainly. Tell me what you know about this area.")
sm.setSpeakerType(4)
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
res = sm.sendNext("#face0#This forest is pretty vast. What are you curious about, more specifically? If I, the adequately intelligent Mascarpo, possess knowledge that can be of help, I'd be glad to share it with you!\r\n\r\n#b#L0# Ask about the ruins. #l")
sm.setSpeakerType(3)
sm.sendNext("#face1#The r-ruins...... You mean that tottering tower of rubble over there? I've heard it was held in high regard in ages past, and even treated as a place of worship because there was some sort of power there that could heal the injured.")
sm.sendSay("#face1#But you know how things go. Time passed, the crowds thinned, and eventually people stopped coming altogether. After that, a rather...unwholesome energy began seeping out of that place.")
sm.sendSay("#face1#Sure, it started small, and most dismissed it, but over the years, it grew stronger, until it became what you see now: creepy ruins that mothers tell their children never to play near.")
sm.setSpeakerType(4)
res = sm.sendNext("#face0#This forest is pretty vast. What are you curious about, more specifically? If I, the adequately intelligent Mascarpo, possess knowledge that can be of help, I'd be glad to share it with you!\r\n\r\n#b#L0# Ask about the relic. #l")
sm.setSpeakerType(3)
sm.sendNext("#face0#In this forest, we sometimes find long-buried parts of the ruins poking up above the surface, but we Karuppa keep our distance from them. They've just got this creepy vibe, you know?")
sm.sendSay("#face0#We did have one weirdo who liked to collect things from the ruins, but for the most part, we avoid anything that has to do with that awful place.")
sm.sendSay("#face1#It almost sounds like you're actually interested in collecting weird trinkets from the ruins yourself...")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face0#(Pssssh. Ancient artifacts are the COOLEST. These blobby talking things are what's weird! Still, it sounds like even if they do know something about the relic, they'd be tight-lipped about it.)")
sm.setSpeakerType(4)
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
res = sm.sendNext("#face0#This forest is pretty vast. What are you curious about, more specifically? If I, the adequately intelligent Mascarpo, possess knowledge that can be of help, I'd be glad to share it with you!\r\n\r\n#b#L0# Ask about his village. #l")
sm.setSpeakerType(3)
sm.sendNext("#face0#Oh, are you interested in our little Karuppa Town?")
sm.sendSay("#face0#Well, 'Karuppa Town' is more of a description than a proper name, really. It's the place where the people who have called this part of the forest home for generations live. I'm a proud Karuppa myself, yes, yes.")
sm.sendSay("#face0#We tend to be a rather faint-hearted lot, so we don't really do much traveling. And by that, I mean we seldom leave the village. We also don't have much in the way of communication with the outside world, aside from the Explorers who pass through from time to time.")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face0#(So they're an indigenous race, or something close, but they've got a collective fear of the ruins that goes back generations? It doesn't sound like I can just waltz up and ask them how to open the entrance again.)")
sm.sendSay("#face6#(It feels like I'm wasting my time poking around here. Maybe it's time I got a move-on and continued my search somewhere else.)")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(1013353) # Mascarpo
sm.sendSay("#face0#If I may be so bold, Explorer, it seems like something about this land has caught your interest. If that's the case, I'd like to invite you to come visit our town. You may be able to learn more there.")
sm.sendSay("#face0#And you're in luck, because today just so happens to be a festival day! Yes indeed, today we celebrate the legendary Karuppa Wunderlixir, greatest of all tonics and tinctures.")
sm.sendSay("#face0#Legend has it that if one chugs a bottle of this famous brew, all vile things within them will be purified, and they'll be blessed with abundant health and longevity.")
sm.setParam(548)
sm.setInnerOverrideSpeakerTemplateID(1013358) # Pathfinder
sm.sendSay("#face3#(Huh. A legendary tonic that purifies all things vile? Not only do I have a very compelling reason to get myself a bottle, it may even have historical ties to this curse.)")
sm.sendSay("#face0#(He's got a dubious-looking face for...a potato? Still, he's the only lead I've got right now. I guess it wouldn't hurt to visit their village and find out what they know.)")
sm.completeQuestNoCheck(parentID)
sm.startQuest(11620)
