# 23012 - Mechanic 2nd job advancement quest

sm.setSpeakerID(2151004)  # Checky
if sm.sendAskYesNo("Would you like to advance to the next level?"):
    sm.completeQuest(parentID)
    sm.jobAdvance(3510)
    sm.sendSayOkay("Congratulations, you are now at the next level! I have given you some SP, enjoy!")
else:
    sm.sendSayOkay("Come back when you're ready.")
