# 23011 - Battle mage 1st job advancement quest

sm.setSpeakerID(2151001)
if not sm.canHold(1382000):
    sm.sendSayOkay("Please make some space in your equipment invetory.")
    sm.dispose()

if sm.sendAskYesNo("Would you like to become a Battle Mage?"):
    sm.completeQuest(parentID)
    sm.jobAdvance(3200)
    sm.resetAP(False, 3200)
    sm.giveItem(1382000, 1)
    sm.sendSayOkay("Congratulations, you are now a battle mage! I have given you some SP and items to start out with, enjoy!")
else:
    sm.sendSayOkay("Of course, you need more time.")
