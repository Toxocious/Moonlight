LADY_SYL = 1056000

sm.setSpeakerID(LADY_SYL)
sm.sendNext("To dual-wield, you must meet two requirements. First, you must reach level 20. Second, you must successfully retrieve a #bMirror of Insight#k, an item that reveals itself only to those worthy of dual-wielding.")
sm.sendSay("The Mirror of Insight grants its owner insight. I can send you to the #bMarble Room in The Secret Garden Depths#k so you can retrieve one. Once there, #bhit marbles#k until one of them turns cloudy when it drops...")

if sm.sendAskAccept("#bDouble-click on the cloudy marble to crack it,#k and you will obtain a Mirror of Insight. That is, assuming the Mirror deems you worthy. I'll send you to the Marble Room when you accept."):
    sm.startQuest(parentID)
    sm.warp(910350000, 0)
else:
    sm.sendNext("Find me when you're ready. Do not keep me waiting.")
    sm.dispose()