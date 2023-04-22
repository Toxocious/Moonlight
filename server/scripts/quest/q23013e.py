# 23013 - Mechanic 1st job advancement quest

sm.setSpeakerID(2151004)  # Checky
if sm.sendAskYesNo("Would you like to become a Mechanic?"):
    if not sm.canHold(1492000) or not sm.canHold(2330000):
        sm.sendSayOkay("Please make some space in your Equipment or Use Inventory.")
        sm.dispose()
    sm.completeQuest(parentID)
    sm.jobAdvance(3500)
    sm.resetAP(False, 3500)
    sm.giveItem(1492000)
    sm.giveItem(2330000, 1600)
    sm.sendSayOkay("Congratulations, you are now a Mechanic! I have given you some SP and items to start out with, enjoy!")
else:
    sm.sendSayOkay("Come back when you're ready.")
