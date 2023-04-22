# id 17657 ([Commerci Republic] Who's That Lady?), field 865030000
sm.setSpeakerID(9390249) # Robed Lady
sm.sendNext("Is it really that important that you find out who I am?")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Hey. We have a bond. You and me, I want us to be open with each other. Like, I just peed myself a little when that wolf bit me. See? You go.")
sm.setParam(0)
res = sm.sendAskYesNo("I hope you're ready for a surprise...")
sm.sendNext("Take this.")
sm.startQuest(parentID)
