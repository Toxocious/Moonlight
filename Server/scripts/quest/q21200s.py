# 21200 - [Job Adv] (Lv.30)   Aran
sm.setSpeakerID(1510009)
if sm.sendAskYesNo("How is your training going? Wow, you've reached such a high level! That's amazing. I knew you would do just fine on Victorial Island... Oh, look at me. I'm wasting your time. I know you're busy, but you'll have to return to the island for a bit."):
    sm.startQuest(parentID)
    sm.sendNext("Your #b#p1201001##k in #b#m140000000##k is acting strange all of a sudden. According to the records, the Polearm acts this way when it is calling for its master. #bPerhaps it's calling for you#k. Please return to the island and check things out.")
    sm.dispose()
else:
    sm.dispose()