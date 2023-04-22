# 22007 |   Collecting Eggs (Evan intro)
sm.setSpeakerID(1013101)
sm.sendNext("Oh, did you bring the egg? Here, give it to me, I'll give you the incubator then.")
if sm.sendAskYesNo("Alright, here you go. I have no idea how to use it, but it's yours.\r\n #fUI/UIWindow2.img/QuestIcon/8/0# 360 exp"):
    sm.giveExp(360)
    sm.completeQuest(parentID)
    sm.dispose()
else:
    sm.dispose()
