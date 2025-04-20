# [Job Adv] Level 30 Bowman

sm.setSpeakerID(1012100)

if (sm.getJob() != "BOWMAN"):
    sm.sendSayOkay("lol go away")
else:
    message = "Congratulations on reaching level 30! bithc pikc a job\r\n\r\n"
    message += "#b#L0#Trail of the Hunter#l\r\n"
    message += "#b#L1#Trail of the Crossbowman#l\r\n"

    choice = sm.sendNext(message)

    if choice == 0:
        sm.jobAdvance(310)
        sm.sendNext("ok you bowman now")
    elif choice == 1:
        sm.jobAdvance(320)
        sm.sendNext("ok you use bolt now")


