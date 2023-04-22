# id 17654 ([Commerci Republic] Screaming in the Night), field 865020071
sm.setSpeakerID(9390216) # Zion
sm.setParam(57)
sm.setColor(1)
sm.sendNext("Assassins!")
sm.setParam(37)
sm.setInnerOverrideSpeakerTemplateID(9390206) # Vaughn Tremier
sm.sendSay("What is this commotion?!")
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendSay("I think it came from over here! ")
sm.setInnerOverrideSpeakerTemplateID(9390227) # Guild Assassin
sm.sendSay("This is getting complicated. We leave, now.")
sm.hideNpcByTemplateIdhi(9390227, True, True)
sm.hideNpcByTemplateIdhi(9390228, True, True)
sm.showNpcSpecialActionByTemplateId(9390203, "summon", 0)
sm.showNpcSpecialActionByTemplateId(9390236, "summon", 0)
sm.showNpcSpecialActionByTemplateId(9390206, "summon", 0)
sm.completeQuestNoCheck(parentID)
sm.createQuestWithQRValue(18418, "B=33285")
