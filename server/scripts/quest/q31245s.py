# id 31245 ([Crimsonheart] Crimsonheart Escape), field 301000000
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1032001) # Grendel the Really Old
sm.sendNext("What did you find?\r\n\r\n#b(You tell Grendel everything you saw.)#k")
sm.sendSay("It sounds as if Tynerum is in chaos. Sacrifices and lawless clans... what a nightmare.")
res = sm.sendAskYesNo("I believe there is more to this tale than meets the eye. Please help the Demons, and see what more you can learn.")
sm.startQuest(parentID)
sm.sendNext("Do take care not to get captured yourself, won't you?\r\n\r\n#b(Return to #p2134012#.)#k")
