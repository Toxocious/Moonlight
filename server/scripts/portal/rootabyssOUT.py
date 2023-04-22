sm.setSpeakerID(1064002)
sm.sendAskYesNo

dialog = str()
if sm.getFieldID() == 105200000:
    sm.warp(105000000)
else:
    if sm.hasMobsInField():
        dialog = "Are you sure you want to leave the battlefield and abandon your party members?"

    else:
        dialog = "Are you sure you want to leave the battlefield?"

    if sm.sendAskYesNo(dialog):
        sm.warpInstanceOut(105200000)