# id 17667 ([Commerci Republic] Plans from Heaven), field 865030111
sm.setSpeakerID(9390238) # Zion
sm.setParam(32)
sm.setColor(1)
sm.sendNext("U-unacceptable...")
sm.setParam(56)
sm.sendSay("Why did you fake your death?")
sm.setParam(32)
sm.sendSay("I don't have to answer your questions. You have no right to hold me here!")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
res = sm.sendAskYesNo("(#h0#, do you think you can get him to talk?)")
sm.startQuest(parentID)
sm.setParam(56)
sm.sendNext("Hey Leon. Hostages don't need their hair, right? Or their creepy little mustaches?")
sm.setParam(36)
sm.sendSay("No, I don't really see why they would. Hey, they don't need their clothes either!")
sm.setParam(56)
sm.sendSay("Yeah, if this guy was bald AND naked, he'd be too embarrassed to leave. I think i've got a tweezer in my bag here...")
sm.setParam(36)
sm.setInnerOverrideSpeakerTemplateID(9390207) # Zion
sm.sendSay("It was the assassins' idea! They are the ones who forced me to fake my own death.")
sm.setParam(56)
sm.sendSay("See? That was easy.")
