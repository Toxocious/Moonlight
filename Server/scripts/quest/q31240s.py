# id 31240 ([Crimsonheart] To Grendel's Library), field 993017200
sm.setSpeakerID(1032001) # Grendel the Really Old
res = sm.sendAskAccept("Do you know of the land of #bTynerum#k? It is a name from ancient history... a land of demons and myth. If you are interested in learning more, pay me a visit in Ellinia. \r\n\r\n#r(You will move to the Magic Library in Ellinia if you accept.)#k")
sm.sendNext("I will await you at the Magic Library.")
sm.setParam(16)
sm.sendSay("(Meet Grendel the Really Old in Ellinia.)")
sm.startQuest(parentID)
sm.warp(101000003)
