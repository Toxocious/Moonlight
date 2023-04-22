# id 17656 ([Commerci Republic] Dances with Wolves 1), field 865030000
sm.setSpeakerID(9390249) # Robed Lady
sm.setParam(2)
sm.sendNext("I think that was the last of them...")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("Are you okay? Are you hurt? Can I get you a soda or something?")
sm.setParam(0)
sm.sendSay("Oh... I-I'm fine... Wait, did you hurt your hand?")
sm.setParam(4)
sm.sendSay("It's just a scratch. One of them was chewing on me a little, but I bit him back, haha!")
sm.setParam(0)
sm.sendSay("You should be more careful! Even a small wound can get infected.")
sm.setParam(2)
sm.sendSay("Would you two just smooch and get it over with? Yeesh. ")
sm.setParam(4)
sm.sendSay("Umm... Ahem... So anyway... If you can tell me your identity... Unless you wanna do the thing my best friend sidekick just said...")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(18418, "B=33287")
