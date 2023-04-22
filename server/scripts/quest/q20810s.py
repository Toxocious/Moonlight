# 20810 - [Job Adv] (Lv.30)   Mihile
sm.setSpeakerID(2520025)
if sm.sendAskYesNo("Congratulations on passing your trials, do you want to become one of my knights?"):
    if not sm.canHold(1302038):
        sm.sendSayOkay("You need inventory space.")

    elif not sm.canHold(1142400):
        sm.sendSayOkay("You need inventory space.")
    else:
        if chr.getJob() == 5100:
            sm.jobAdvance(5110)
            sm.giveItem(1302038)
            sm.giveItem(1142400)
            sm.completeQuest(20810)
            sm.dispose()
        else:
            sm.sendNext("You are not a mihile class.")
            sm.dispose()
