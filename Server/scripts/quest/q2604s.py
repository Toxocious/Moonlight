RYDEN = 1057001

sm.setSpeakerID(RYDEN)
sm.sendNext("How interesting. New recruit, you didn't even bat an eyelash at Shiba's insults... It was all a setup, you see. We wanted to see how you would react. You did quite well.")
sm.sendSay("You have a knack for our Dual Blade ways. #bI recommend you for the special mission!#k ")
sm.sendSay("I can't tell you what kind of mission it is. That's up to #bLady Syl#k, IF she agrees that you're worthy. If not, you'll train the same way as everyone else. So, try not to tick Lady Syl off.")

if sm.sendAskAccept("When you accept, I'll send you to Lady Syl."):
    sm.startQuest(parentID)
    sm.removeNpc(RYDEN)
    sm.warp(103050101, 0)