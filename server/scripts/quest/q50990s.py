# id 50990 ([Theme Dungeon] Ellinel Fairy Academy ), field 993017200
sm.setSpeakerID(9130008) # Mouri Motonari
res = sm.sendAskAccept("You seem to be prepared for a new adventure. Can you make some time for me? We were asked to look for someone with the appropriate qualifications.")
sm.sendNext("The request was from #bEllinel Fairy Academy#k. A young human entered the Academy and it's caused quite a disturbance.")
sm.sendSay("I don't know all the details, but they are in need of help and I think it is best that we look into the situation.")
res = sm.sendAskYesNo("Fanzy will take you into the land of the fairies. I can send you to him directly, if you'd like.")
sm.sendNext("I shall send you to Fanzy right away. Complete your mission, and come straight back. Good luck.")
sm.startQuest(parentID)
sm.startQuest(32148)
sm.warp(101030000)
