# id 1500017 (Tosh the Fairy), field 101073010
sm.setSpeakerType(3)
if not sm.hasMobsInField():
    sm.setParam(5)
    sm.setInnerOverrideSpeakerTemplateID(1500017) # Tosh the Fairy
    sm.sendNext("I-I was so scared...")
    sm.sendSay("Me and the others were rehearsing a play when the Mandrakies went all crazy on us. I closed my eyes when one of them chomped on my leg and then I woke up here!")
    sm.setParam(17)
    sm.sendSay("#b(One student's better than nothing. I'd better get this kid back to Ellinel.)#k")
    sm.warp(101073000)
    sm.completeQuest(32123)
else:
    sm.sendNext("Please kill all the mobs, I can't get out like this!")
