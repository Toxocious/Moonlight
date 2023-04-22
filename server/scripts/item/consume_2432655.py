# id 2432655 (Mushking's Summons), field 993017200
sm.setSpeakerID(9010000) # Maple Administrator
sm.setParam(2)
res = sm.sendAskAccept("All set for Mushroom Castle?\r\n\r\n#r(You will be moved to the Mushroom Castle Entrance when you accept.)")
sm.warp(106030100)
sm.consumeItem(parentID)
