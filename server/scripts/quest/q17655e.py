# id 17655 ([Commerci Republic] False Charges), field 865030000
sm.setSpeakerID(9390249) # Robed Lady
sm.setParam(2)
sm.sendNext("Wait! What are you doing here? Also where is here?")
sm.setParam(0)
sm.sendSay("Somewhere far from San Commerci.")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Wow, you're so helpful. You're always helping us out. You're pretty awesome.")
sm.setParam(0)
sm.sendSay("I am not your guardian angel. I'm trying to collect the payment you owe me.")
sm.setParam(4)
sm.sendSay("Oh, yeah, right... Well, I just gotta clear up this thing with my dad first. Maybe you could tell me who you are, and that would help?")
sm.setParam(2)
sm.sendSay("Yeah, what's going on here, lady? You're using some pretty advanced magic.")
sm.setParam(0)
sm.sendSay("Hmm...")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(18418, "B=33286")
