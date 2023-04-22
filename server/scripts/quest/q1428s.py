#   [Job Adv] (Lv.30)   Cannoneer
sm.setSpeakerID(1072008)
sm.sendNext("You must prove yourself before you can advance to Cannoneer. Pass the test first!")
if sm.sendAskYesNo("Fight the OctoPirates and retrieve a Dark Marble from them. This will awaken your Mirror of Insight. I'll send you over immediately once you accept"):
    sm.warp(912040000, 0)
    sm.startQuestNoCheck(parentID)
else:
    sm.sendSayOkay("You cannot stay a mere Pirate. You #bwill#k have to face up to the test.\r\n"
                   "Talk to me when you are ready.")
sm.dispose()
