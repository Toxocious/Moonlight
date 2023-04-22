# id 31258 ([Crimsonheart] Outside the Castle), field 301000000
sm.setSpeakerType(3)
sm.setParam(16)
sm.sendNext("(Tell Grendel everything you heard from Ridley.)")
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(1032001) # Grendel the Really Old
sm.sendSay("A war led by the demon Naricain, eh? Fascinating...")
sm.setParam(4)
res = sm.sendAskYesNo("Can you see what's outside Crimsonheart Castle? Perhaps a greater perspective would help.")
sm.startQuest(parentID)
sm.setParam(5)
sm.sendNext("Very well.\r\n(Exit the Keep through the door on the bottom right.)")
