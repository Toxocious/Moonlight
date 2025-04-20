# 20411 - [Job Adv] (Lv.100) Mihile 4rd job adv
sm.setSpeakerID(1101002)
sm.sendNext("Cygnus is safe and the knights will be back to normal soon, I've even heard some of them referring to you as the new Chief Knight. It looks like you have no choice but to take up my proposal.")
if not sm.canHold(1142402):
    sm.sendSayOkay("You need inventory space.")
    sm.dispose()
else:
    if chr.getJob() == 5111:
        sm.jobAdvance(5112)
        sm.giveItem(1142402)
        sm.dispose()
    else:
        sm.sendNext("You are not a mihile class.")
        sm.dispose()