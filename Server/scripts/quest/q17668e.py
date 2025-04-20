# id 17668 ([Commerci Republic] Twice Cooked), field 865030300
sm.setSpeakerID(9390237) # Claire Tremier
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendNext("Are you okay Claire? Did they hurt you? I will fight anyone who hurt you!")
sm.setParam(0)
sm.sendSay("I'm fine. I'm glad you're both alive.")
sm.setParam(4)
sm.sendSay("I'm glad you're alive too, Claire. Just in general.")
sm.setParam(0)
sm.sendSay("What happened to Zion and his henchmen?")
sm.setParam(2)
sm.sendSay("I've got some bad news... Zion was assassinated. We couldn't save him.")
sm.setParam(0)
sm.sendSay("Zion is... dead?")
sm.setParam(4)
sm.sendSay("But we're all alive!")
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(18418, "B=33300")
