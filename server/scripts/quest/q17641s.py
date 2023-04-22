# id 17641 ([Commerci Republic] Robed Adventures), field 865000000
sm.setSpeakerID(9390235) # Leon Daniella
sm.sendNext("Hey, you're late! Getting cold feet?")
sm.setParam(2)
sm.sendSay("Nah, I was...delayed.")
sm.setParam(0)
res = sm.sendAskYesNo("Hm, whatever. You sure you're ready? We're gonna be on a boat. A boat!")
sm.setParam(32)
sm.setColor(1)
sm.sendNext("As captain, I have important stuff to do. Hang on.")
sm.startQuest(parentID)
sm.setParam(56)
sm.sendSay("No prob. Let's go.")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(9390204) # Robed Lady
sm.sendSay("Oh? Another coincidence.")
sm.setParam(56)
sm.sendSay("............")
