# id 865000004 (null), field 865000004
sm.lockInGameUI(True, False)
sm.sendDelay(2000)
sm.setSpeakerType(3)
sm.setParam(35)
sm.setColor(1)
sm.sendNext("Man, I can't sleep tonight. The air's too... moist.")
sm.sendSay("I'm gonna take a walk, clear my head.")
sm.forcedInput(2)
sm.sendDelay(1500)
sm.forcedInput(0)
sm.sendNext("What's that noise? Maybe it's Leon...")
sm.sendDelay(2000)
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendNext("Aw, c'mon, daddy. It's not like that.")
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("You can't just go around trusting strangers, Leon.")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("But #h0# isn't a stranger. #h0# is my sidekick! #h0# helped me!")
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("How do you know the whole thing wasn't staged?")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Father...")
sm.setInnerOverrideSpeakerTemplateID(9390203) # Gilberto Daniella
sm.sendSay("Listen to me, son. One day, I'm going to pass the Merchant Union to you. You can't go around trusting every person who smiles at you.")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("I... I can't?")
sm.setParam(35)
sm.sendSay("(I knew it. Gilberto doesn't trust me. I have to think of a way to change his mind!)")
sm.completeQuestNoCheck(17623)
sm.completeQuestNoCheck(17712)
sm.startQuest(17720)
sm.lockInGameUI(False, True)
sm.warp(865000002)
