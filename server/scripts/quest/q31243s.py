# id 31243 ([Crimsonheart] The Demons of Shadow Veil), field 301000000
sm.setSpeakerID(2134009) # Pepper
sm.setParam(16)
sm.sendNext("#b(You told Pepper why you are here.)#k")
sm.setParam(0)
res = sm.sendNext("You don't look like the other people of Tynerum. Are you from Shadow Veil, like us? \r\n\r\n#b#L0#I came here through the Dimensional Mirror. Is there someone I can get to help you?#l")
res = sm.sendNext("You-you're from another dimension? ...I don't know if I can believe you, but I guess I don't have any other choice. Besides, you are the first person who hasn't attempted to harm us in ages. My people were foraging for food in the Shadow Veil Forest when monsters came and took us.\r\n\r\n#b#L0#Why did they kidnap you?#l")
res = sm.sendNext("I-I believe we are meant to be #bsacrificed#k.\r\n\r\n#b#L0#Sacrificed?!#l")
res = sm.sendNext("Maybe you really ARE from a different dimension... People have been disappearing around here for years. I'm not sure why you came here though... we never sent out a distress call.\r\n\r\n#b#L0#Is there a sorcerer named #p2134012# here?#l")
res = sm.sendAskYesNo("Never heard of him.")
sm.startQuest(parentID)
sm.sendSayOkay("There's no one named #p2134012# here.\r\n\r\n#b(Search for #p2134012#.)")
