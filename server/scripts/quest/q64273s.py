# id 64273 ([MONAD] Abrup Mission Board Unlocked!), field 867200110
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400593) # Hawalu
sm.sendNext("Hi!")
sm.sendSay("My mommy, Ullan, and some other villagers prepared this for you.")
sm.setInnerOverrideSpeakerTemplateID(9400588) # Ullan
sm.sendSay("Hawalu, what are you doing over there?")
sm.setInnerOverrideSpeakerTemplateID(9400593) # Hawalu
sm.sendSay("I wanna tell them about the Mission Board!")
sm.setInnerOverrideSpeakerTemplateID(9400588) # Ullan
sm.sendSay("You're not old enough to explain well yet. I'll take it from here.")
sm.sendSay("Thank you for helping us so much.\r\nWe all got together to think of a way to pay you back and prepared this #eMission Board#n!")
sm.openUI(1886)
sm.sendSay("The #eMission Board#n has #bDaily Missions#k, #bWeekly Missions#k, and #bAchievement Missions#k.")
sm.sendSay("#bDaily Missions#k are tasks that can be completed every day. Just don't forget that they #breset at midnight#k!")
sm.sendSay("#bWeekly Missions#k can be done once a week. You'll have no trouble completing them if you visit Abrup every day! \r\nWeekly missions are#b reset every Wednesday#k, so make sure you claim your gift before then!")
sm.sendSay("#bAchievement Missions#k are missions that can only be completed once. We really wanted to repay you for your hard work, so we prepared amazing gifts for you!")
sm.sendSay("You can view the gift you'll receive for completing each mission when you place your cursor above the gift box icon for it.")
sm.sendSay("There are also great titles like #i3700493:# #t3700493#, #i3700502:# #t3700502#, and #i3700496:# #t3700496#, and there are useful items like #i2048724:# #t2048724# and #i2028372:# #t2028372#, so don't forget to take them with you! ")
sm.setInnerOverrideSpeakerTemplateID(9400593) # Hawalu
sm.sendSay("Don't forget to take them!")
sm.setInnerOverrideSpeakerTemplateID(9400588) # Ullan
sm.sendSay("See you later!")
sm.setInnerOverrideSpeakerTemplateID(9400593) # Hawalu
sm.sendSay("See you later!")
sm.createQuestWithQRValue(parentID, "chk=1")
sm.completeQuestNoCheck(parentID)
