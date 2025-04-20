# [Riena Strait] Wood That's Good 3
sm.setSpeakerID(1510006)

lumber = 4030022

if sm.canHold(lumber):
    sm.sendNext("Navigator, I see decent lumber!\r\n\r\n"
                "#b#v"+ str(lumber) +"##t"+ str(lumber) +"#")
    sm.startQuestNoCheck(parentID)
    sm.giveItem(lumber)
else:
    sm.sendSayOkay("Navigator, let's make some space for all this lumber we're retrieving!")
sm.dispose()
