# [Job Adv] Level 30 Magician

sm.setSpeakerID(1012100)

if (sm.getJob() != "MAGICIAN"):
    sm.sendSayOkay("lol go away")
else:
    message = "Congratulations on reaching level 30! bithc pikc a job\r\n\r\n"
    message += "#b#L0#Path of Fire and Poison#l\r\n"
    message += "#b#L1#Path of Ice and Lightning#l\r\n"
    message += "#b#L2#Path of the Cleric#l\r\n"

    choice = sm.sendNext(message)

    if choice == 0:
        sm.jobAdvance(210)
        sm.sendNext("lol u farted")
    elif choice == 1:
        sm.jobAdvance(220)
        sm.sendNext("wow ice")
    elif choice == 2:
        sm.jobAdvance(230)
        sm.sendNext("lol imagine healing")