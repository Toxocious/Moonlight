 # Spiruna (2032001) | Old Man's House (200050001)
 # The Book of Ancient is Back (Quest 3034)
 # Author: Tiger

mesos = 500000

if sm.hasQuestCompleted(3034): # The Book of Ancient is Back
    response = sm.sendAskYesNo("You've been so much of a help to me... If you have any #t" + str(4004004) + "#, I can refine it for you for only #b" + str(mesos) + " meso#k each.")

    if response:
        answer = sm.sendAskNumber("Okay, so how many do you want me to make?", 1, 1, 100)

        totalCost = answer * mesos
        totalQty = answer * 10

        if not sm.canHold(4005004): # Not enough space
            sm.sendSayOkay("Please make some more space in your inventory.")

        elif sm.getMesos() < totalCost: # Too little mesos
            sm.sendSayOkay("I'm sorry, but I am NOT doing this for free.")

        elif not sm.hasItem(4004004, totalQty): # Does not have enough Dark Crystal Ores, TODO: check if this looks right (for 5 DC you need 50 DCO?)
            sm.sendSayOkay("I need that ore to refine the Crystal. No exceptions..")

        else:   # can afford & can hold
            sm.consumeItem(4004004, totalQty)
            sm.giveItem(4005004, answer)
            sm.deductMesos(totalCost)
            sm.sendSayOkay("Use it wisely.")
else:
    sm.sendSayOkay("Go away, I'm trying to meditate.")
