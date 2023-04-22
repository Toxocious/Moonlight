# [Job Adv] Level 30 Pirate

sm.setSpeakerID(1012100)

if (sm.getJob() != "PIRATE"):
    sm.sendSayOkay("lol go away")
else:
    message = "Congratulations on reaching level 30! bithc pikc a job\r\n\r\n"
    message += "#b#L0#Brawler of the High Seas#l\r\n"
    message += "#b#L1#Gunslinger of the Seven Seas#l\r\n"

    choice = sm.sendNext(message)

    if choice == 0:
        sm.jobAdvance(510)
        sm.sendNext("ok you punch")
    elif choice == 1:
        sm.jobAdvance(520)
        sm.sendNext("ok you gun")