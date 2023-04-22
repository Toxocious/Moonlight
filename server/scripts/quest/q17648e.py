# id 17648 ([Commerci Republic] Back to San Commerci), field 865000000
sm.setSpeakerID(9390235) # Leon Daniella
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendNext("Oh... Hey... What's up?")
sm.setInnerOverrideSpeakerTemplateID(9390204) # Robed Lady
sm.sendSay("It's time we went our separate ways.")
sm.hideNpcByTemplateIdhi(9390255, True, True)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("How about a name first?")
sm.setParam(2)
sm.sendSay("What would you do with her name?")
sm.setParam(4)
sm.sendSay("I'd probably say it sometimes, maybe to you. Because we're friends now, right?")
sm.setParam(2)
sm.sendSay("Man, these people... Hey, why's everybody running around like crazy?")
sm.setParam(4)
sm.sendSay("I dunno, I thought there might be a sale on pies or something. My buddy is standing over there, if you want to know.")
sm.setParam(2)
sm.sendSay("Is there an event in town or something?")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390217) # Tepes
sm.sendSay("Leon, you're back just in time!")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("What's with the commotion? Are they throwing a party for me?")
sm.setInnerOverrideSpeakerTemplateID(9390217) # Tepes
sm.sendSay("The Heaven Empire sent somebody here again!")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("The Heaven Empire? What are those annoying snobs up to now?")
sm.setParam(2)
sm.sendSay("What is the Heaven Empire?")
sm.setParam(4)
sm.sendSay("Haha, #h0#! You don't know anything! I'll tell you later. I can't stop thinking about that pie I talked about.")
sm.sendSay("Oh, hey! I found this junk on Captain Blood's ship! Do you want it?")
sm.completeQuestNoCheck(parentID)
sm.completeQuestNoCheck(17731)
sm.createQuestWithQRValue(18418, "B=33277")
sm.createQuestWithQRValue(18418, "B=33278")
sm.createQuestWithQRValue(18418, "B=33279")
