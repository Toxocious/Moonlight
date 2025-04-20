# id 17640 ([Commerci Republic] Pack Up, and Set Sail 3), field 865000002
sm.setSpeakerID(9390262) # Leon Daniella
sm.sendNext("Hey, you made it! I was gonna leave without you. Where's my stuff?")
sm.setParam(2)
sm.sendSay("Kentucky at the General Store told me they deliver.")
sm.setParam(0)
res = sm.sendAskYesNo("No kidding! Man, they should, like, advertise that. Anyway, I got everyone together. Ready to roll? Er, sail?")
sm.startQuest(parentID)
sm.setParam(2)
sm.sendNext("(Changing the subject, huh?) Yup. I'm all set. ")
sm.setParam(0)
sm.sendSay("Righteous! I'll meet you at the dock.")
