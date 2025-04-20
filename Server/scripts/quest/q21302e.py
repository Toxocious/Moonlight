# 21302 - [Job Adv] (Lv.60)   Aran
sm.setSpeakerID(1201002)
sm.sendNext("Oh, isn't that... Hey, did you remember how to make the Red Jade? You may be a dummy who has amnesia, but this is why I can't leave you. Now hurry, give me the gem!")
if sm.sendAskYesNo("Okay, now that I have the power of Red Jade, I'll restore more of your abilities. Your level has gotten much higher since the last time we met, so I'm sure I can work my  magic a bit more this time!"):
    if not sm.canHold(1142131):
        sm.sendSayOkay("Please make some space in your equipment inventory.")
        sm.dispose()
    sm.completeQuest(parentID)
    sm.giveItem(1142131)
    sm.jobAdvance(2111)
    sm.consumeItem(4032312)
    sm.sendNext("Please get back all of your abilities soon. I want to explore with you like we did in the good old days.")
