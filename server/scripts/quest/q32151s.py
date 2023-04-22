# [Theme Dungeon] Ellinel Fairy Academy

response = sm.sendAskYesNo("#h #, could you spare me a moment? I received a request for help, and i can't think of anyone better than you.")

if response:
    sm.sendNext("There has been an incident at the #b Ellinel Fairy Academy#k. A human magician has trespassed in the sacred halls of the fairy school.")
else:
    sm.dispose();

sm.sendNext("I don't know all the details, but i know our relationship with the fairies is strained enough as it is. Will you go to the North Forest near Elinia and meet with #p1040002#.")

response = sm.sendAskYesNo("Fanzy will take you into the land of the fairies. I can send you to him directly, if you'd like")

if response:
    sm.startQuestNoCheck(32151)
    sm.warp(101030000)
    sm.dispose()
else:
    sm.startQuestNoCheck(32151)
    sm.dispose()
