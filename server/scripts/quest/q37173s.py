# id 37173 ([Elodin] Singing Necessities 1), field 101084400
sm.setSpeakerID(1501004) # Shimmer Songbird
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1501015) # Shimmer Songbird
sm.sendNext("Before you can sing, you have to prep your voice.")
sm.sendSay("Drinking #i4036505# #t4036505# from the #o3501108#s and #o3501109#s nearby will give you nice, clear notes.")
res = sm.sendAskYesNo("Here's another Small Bottle. Please fill it with Pure Water and come back.")
sm.startQuest(parentID)
sm.startQuest(parentID)
sm.sendNext("I'll need #r15 droplets of Pure Water#k this time.")
sm.warp(101084100)
