# 21401 - [Job Adv] (Lv.100)   Aran
sm.setSpeakerID(1201002)
sm.sendNext("Why do I look like this, you ask? I don't want to talk about it, but I suppose I can't hide from you since you're my master...")
sm.sendNext("While you were trapped inside ice for hundreds of years, I, too, was frozen. It was a long time to be away from you. That's when the seed of darkness was planted in my heart.")
sm.sendNext("But since you awoke, I thought the darkness had gone away. I thought things would return to the way they were, but I was mistaken.")
if sm.sendAskYesNo("Please, Aran. Please stop me from becoming enraged. Only you can control me. It's out of my hands now. Please do whatever it takes to #rstop me from going berserk#k!"):
    sm.startQuest(parentID)
    sm.warpInstanceIn(914020000)
    sm.setInstanceTime(20 * 60, 140000000)