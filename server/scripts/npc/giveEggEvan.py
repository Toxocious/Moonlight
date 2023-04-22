if sm.hasQuest(22007):
    if not sm.hasItem(4032451):
        if sm.canHold(4032451):
            sm.giveItem(4032451)
            sm.dispose()
        else:
            sm.sendSay("Please make room in your Etc Inventory.")
            sm.dispose()
sm.dispose()