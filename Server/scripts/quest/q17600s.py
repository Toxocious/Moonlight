# [Commerci Republic] Neinheart's Call

sm.setSpeakerID(1064026) # Neinheart
response = sm.sendAskYesNo("Ah good, I've managed to reach you. The Empress has been asking for you. Could you come to Ereve?\r\n"
                "#b(You will be moved to Ereve if you accept.)")


if response:
    sm.sendNext("I will be waiting for you.")
else:
    sm.sendSayOkay("Let me know once you are ready.")
    sm.dispose()

sm.startQuestNoCheck(parentID)
sm.warp(130000000, 0)
sm.dispose()