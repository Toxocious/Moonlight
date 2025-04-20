# 22003 |   Delivering the Lunch Box (Evan intro)
sm.setSpeakerID(1013100)
if sm.sendAskYesNo("Your #bDad#k forgot his Lunch Box when he left for the farm this morning. Will you #bdeliver this Lunch Box#k to your Dad in #bFarm Center#k, honey?"):
    if sm.canHold(4032448):
        sm.sendNext("Heehee, my Evan is such a good kid! Head #bleft after you exit the house#k. Rush over to your Dad, I'm sure he's starving.")
        sm.sendNext("Come back to me if you happen to lose the Lunch Box, I'll make his lunch again.")
        sm.startQuest(parentID)
        sm.dispose()
    else:
        sm.sendNext("Please make room in your Etc Inventory.")
        sm.dispose()
else:
    sm.dispose()

