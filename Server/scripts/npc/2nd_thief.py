# [Job Adv] Level 30 Thief

sm.setSpeakerID(1012100)

if (sm.getJob() != "MAGICIAN"):
    sm.sendSayOkay("lol go away")
else:
    message = "Congratulations on reaching level 30! bithc pikc a job\r\n\r\n"
    message += "#b#L0#Path of the Assassin#l\r\n"
    message += "#b#L1#Path of the Bandit#l\r\n"

    choice = sm.sendNext(message)

    if choice == 0:
        sm.jobAdvance(410)
        sm.sendNext("ok u ass ass in")
    elif choice == 1:
        sm.jobAdvance(420)
        sm.sendNext("ok stabby")