# 22001 |   Feeding Bull Dog (Evan intro)
sm.setSpeakerID(1013101)
sm.sendNext("Haha, I had a good laugh. Hahaha, but enough with that nonsense, feed Bulldog, would you?")
sm.setPlayerAsSpeaker()
sm.sendNext("#bWhat? that's Utah's job!")
sm.setSpeakerID(1013101)
if sm.sendAskYesNo("You little brat! I told you to call me older brother! You know how much Bulldog hates me. He'll bite me if I go near him. You feed him. He likes you."):
    if sm.canHold(4032447):
        sm.startQuest(parentID)
        sm.giveItem(4032447)
        sm.sendNext("Hurry up and head #bleft#k to feed #bBulldog#k. He's been barking to be fed all morning.")
        sm.sendSay("Feed Bulldog and come back to see me.")
        sm.dispose()
    else:
        sm.sendSay("Please make space in your Etc Inventory.")
        sm.dispose()
else:
    sm.dispose()


