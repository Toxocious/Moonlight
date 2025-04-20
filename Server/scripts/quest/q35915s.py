# id 35915 (Gorgonz Order 1), field 100051030
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(1013351) # Gorgonz
sm.sendNext("#face0#I'm willing to share this useful and rather timely information...IF you'll do a little favor for me. ...What? Don't make that face. It won't be that hard. Probably.")
sm.sendSay("#face0#Brie probably mentioned that I collect antiques. It's sort of a hobby of mine, or at least a way to pretend all the junk I keep lying around serves some sort of higher purpose. But ever since the compass broke, I haven't had much luck tracking down new pieces for my collection.")
sm.sendSay("#face1#That means I've gotta resort to...other methods to feed my collection. I've heard that the monsters nearby sometimes drop antiques if you beat them up, hint-hint.")
sm.setSpeakerType(4)
sm.setSpeakerID(1013311) # Gorgonz
res = sm.sendAskAccept("#face1#So yeah, if you're feeling spry, could you bring me #b#i4036527# #t4036527##k x#b15#k? You should be able to get 'em from #r#o2300203#s#k. Hoppin' Sprouts are monsters I've often spotted in the sensibly named #r#m100051022##k.")
sm.setSpeakerType(3)
sm.sendNext("#face0#Those pots the Hoppin' Sprouts like to bop around in are actually valuable antiques. Even the broken pieces make nice additions to my collection. Anyhow, I'm leaving things in your capable hands.")
sm.startQuest(parentID)
