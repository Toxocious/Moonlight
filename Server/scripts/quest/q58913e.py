# id 58913 ([Hieizan Temple] Investigating Hieizan), field 811000008
sm.setSpeakerID(9130103) # Ayame
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(9130103) # Ayame
sm.sendNext("Thanks for finding this! ")
sm.sendSay("Good thing the Demon King couldn't be summoned completely... But y'know, I'm actually a little sad Princess No lost herself like that.")
sm.sendSay("Please defeat her for me. It's all we can do now. ")
sm.completeQuestNoCheck(parentID)
sm.sendSay("To stop Princess No, you need the key to Hieizan Temple. That's where she's holed up. ")
sm.createQuestWithQRValue(18418, "B=35658")
sm.sendSay("She left traces of herself at the small shrine of Hieizan when she ran. Her alter ego will drop the key to the real Princess No.")
sm.sendSay("I managed to sneak a copy of the key. Let me know if you want to use it to go to Princess No.")
sm.sendPrev("Be careful. Princess No will be a challenging foe to contend with. Talk to me if you'd like to practice first, and I can send you to a training area.")
sm.createQuestWithQRValue(18418, "B=35659")
