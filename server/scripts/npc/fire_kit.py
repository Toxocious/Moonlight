if sm.hasQuest(2566):
    if sm.hasItem(4032985):
        sm.chatScript("You already have the Ignition Device.")
    else:
        sm.giveItem(4032985)
        sm.chatScript("Ignition Device. Bring ")