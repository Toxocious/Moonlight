# id 32136 | [Theme Dungeon] Ellinel Fairy Academy
sm.setSpeakerID(10201)
sm.sendNext("Could you spare me a moment? I received a request for help, and I can' think of anyone better than you.")
sm.sendNext("There has been an incident at the #bEllinel Fairy Academy#k. A human magician has trespassed in the sacred halls of the fairy school.")
if sm.sendAskYesNo("Fanzy will take you into the land of the fairies. I can send you to him directly, if you'd like."):
    sm.warp(101030000)
    sm.startQuest(parentID)