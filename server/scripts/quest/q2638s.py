#   [Job Adv] (Lv.30)   Becoming a Blade Acolyte
sm.setSpeakerID(1056000)
sm.sendNext("You must prove yourself before you can advance to Blade Acolyte. Pass the test first!")
if sm.sendAskYesNo("Fight the Shadow  Blade Lords and retrieve a Dark Marble from them. This will awaken your Mirror of Insight. I'll send you over immediately once you accept"):
    sm.warp(910350300, 0)
    sm.startQuestNoCheck(parentID)
else:
    sm.sendSayOkay("You cannot stay a mere Blade Recruit. You #bwill#k have to face up to the test.\r\n"
                   "Talk to me when you are ready.")
sm.dispose()
