# [Job Adv] Level 30 Warrior

sm.setSpeakerID(1012100)

if (sm.getJob() != "WARRIOR"):
    sm.sendSayOkay("lol go away")
else:
    message = "Congratulations on reaching level 30! bithc pikc a job\r\n\r\n"
    message += "#b#L0#Way of the Fighter#l\r\n"
    message += "#b#L1#Way of the Page#l\r\n"
    message += "#b#L2#Way of the Spearman#l\r\n"

    choice = sm.sendNext(message)

    if choice == 0:
        sm.jobAdvance(110)
        sm.sendNext("ok you use sord")
    elif choice == 1:
        sm.jobAdvance(120)
        sm.sendNext("ok you use pole")
    elif choice == 2:
        sm.jobAdvance(130)
        sm.sendNext("ok you use big hammer")