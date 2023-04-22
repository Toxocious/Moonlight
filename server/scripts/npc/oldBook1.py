# Alcaster (2020005)  |  El Nath

items = [
    # itemId, meso
    [2050003, 300],     # Holy Water
    [2050004, 400],     # All Cure
    [4006000, 5000],    # Magic Rock
    [4006001, 5000]     # Summon Rock
]


if sm.hasQuestCompleted(3035): # Alcaster's Book of Ancient Quest  -  (Last quest of the questline)
    sm.sendNext("What is it?\r\n#b"
                "#L0#I want to buy something really rare.#l")


    text = "Thanks to you. #bThe Book of Ancient#k is safely sealed. " \
           "As a result, I used up about half of the power I have accumulated over the last 800 years...but can now die in peace. " \
           "Would you happen to be looking for rare items by any chance? As a sign of appreciation for your hard work. " \
           "I'll sell some items in my possession to you and ONLY you. Pick out the one you want!\r\n #b"
    for i in range(len(items)):
        text += "#L"+ str(i) +"##z"+ str(items[i][0]) +"# - "+ str(items[i][1]) +" mesos#l\r\n"
    selection = sm.sendNext(text)


    number = sm.sendAskNumber("#b#z"+ str(items[selection][0]) +"##k?\r\n"
                    "Since you helped me, I'll sell it to you for cheap. \r\n"
                    "It'll cost you #b"+ str(items[selection][1]) +" mesos#k each. \r\n"
                    "How many would you like?",
                    1, 1, 100)


    totalCost = number * items[selection][1] # amount selected * meso cost
    if not sm.canHold(items[selection][0]): # Not enough space
        sm.sendSayOkay("Please make some more space in your inventory.")

    elif sm.getMesos() < totalCost: # Too little mesos
        sm.sendSayOkay("It seems you don't have enough mesos.")

    else:   # can afford & can hold
        sm.giveItem(items[selection][0], number)
        sm.deductMesos(totalCost)
        sm.sendSayOkay("Thanks for your purchase.")


else:
    sm.sendSayOkay("I worry about #bThe Book of Ancient#k...")