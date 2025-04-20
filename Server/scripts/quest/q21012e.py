# 140090300
PURUN = 1202004

sm.setSpeakerID(PURUN)
if sm.sendAskYesNo("Hm... Your expression tells me that the exercise didn't jog any memories. But don't you worry. They'll come back, eventually. Here, drink this potion and power up!\r\n#fUI/UIWindow2.img/QuestIcon/4/0# \r\n#i2000022# 10 #t2000022# \r\n#i2000023# 10 #t2000023# \r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 57 exp"):
    sm.giveItem(2000022, 10)
    sm.giveItem(2000023, 10)
    sm.giveExp(57)
    sm.completeQuest(parentID)

    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(Even if you're really the hero everyone says you are... What good are you without any skills?)#k")
else:
    sm.sendNext("What? You don't want the potion?")
    sm.dispose()