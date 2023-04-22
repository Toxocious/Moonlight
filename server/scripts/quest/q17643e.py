# id 17643 ([Commerci Republic] Yar! Pirates!), field 865010001
sm.setSpeakerID(9390244) # Leon Daniella
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390217) # Tepes
sm.sendNext("The pirates are getting away! Good riddance!")
sm.setParam(0)
sm.sendSay("Nooooo, my glory! We can't let them go!")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390204) # Robed Lady
sm.sendSay("It's my turn to show you what I can do. I'll #r#etear the ship apart#k#n with my magic.")
sm.setParam(2)
sm.sendSay("Ah! Wait, wait, wait, WAIT!")
sm.setParam(4)
sm.sendSay("Why?")
sm.setParam(2)
sm.sendSay("Captain Blood isn't here yet. They'll lead us back to him, so we should just follow along!")
sm.setParam(0)
sm.sendSay("Brilliant! Let's do the following thing!")
sm.setParam(4)
sm.sendSay("Hm... That's too bad.")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(18418, "B=33269")
