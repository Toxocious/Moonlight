# 22008 |   Chasing away the foxes (Evan intro)
sm.setSpeakerID(1013101)
if sm.sendAskYesNo("It's strange. The chickens are acting funny. They used to hatch way more Eggs. Do you think the Foxes have something to do with it? If so, we better hurry up and do something about it."):
    sm.startQuest(parentID)
    sm.sendNext("Right? Let us go and defeat those Foxes. Go on ahead and defeat #r10 Treacherous Foxes#k in #bBack Yard#k first. I'll follow you and take care of what's left behind. Now, hurry over to the Back Yard!")
    sm.dispose()
else:
    sm.dispose()
