# id 34813 (For the Verdant Flora), field 402000501
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3001306) # Soldier
sm.sendNext("#face0#Well yeah. Don't just stand there. Help us out.\r\nWe need to extract mana from nearby mytocrystals...")
sm.setSpeakerType(4)
sm.setSpeakerID(3001336) # Soldier
sm.setParam(132)
res = sm.sendNext("#face0##fUI/tutorial.img/illium/mob1/0##fUI/tutorial.img/illium/mob2/0##fUI/tutorial.img/illium/mob3/0#\r\nThink you can get mana from #b20#k of these crystals?\r\n#b#L0#Of course!#k\r\n#b#L1#I don't think so...#k")
sm.startQuest(parentID)
sm.setSpeakerType(3)
sm.setParam(37)
sm.sendNext("#face0#Great. There are #o9101116# items in the #m402000502# to the right of here. Now, get moving. We don't have all day.")
