# 23012 - Mechanic 3rd job advancement quest

sm.setSpeakerID(2151004)  # Belle
if sm.sendAskYesNo("Would you like to advance to the next level?"):
    sm.completeQuest(parentID)
    sm.jobAdvance(3511)
    sm.sendSayOkay("Congratulations, you are now at the next level! I have given you some SP, enjoy!")
else:
    sm.sendSayOkay("Come back when you're ready.")
