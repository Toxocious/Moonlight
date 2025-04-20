# id 17648 ([Commerci Republic] Back to San Commerci), field 865010001
sm.setSpeakerID(9390244) # Leon Daniella
res = sm.sendAskYesNo("Well the pirate guy is gone, I guess. Let's go tell dad! He'll be so excited. I know I am!")
sm.setParam(1)
sm.sendNext("Change the heading! We head for San Commerci!")
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(9390217) # Tepes
sm.sendSay("Aye-Aye! Just let me get my navigating scarf on...")
sm.startQuest(parentID)
sm.warp(865000000)
