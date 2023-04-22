# 23023 - 2nd job advancement Battle Mage

sm.setSpeakerID(2151001)
if sm.sendAskYesNo("Are you ready to advance to the next level?"):
    sm.completeQuest(parentID)
    sm.jobAdvance(3210)
    sm.consumeItem(4032737)
    sm.sendSayOkay("Good job on finding the report. I've molded you into the next level of being a Battle Mage.")
else:
    sm.sendSayOkay("Come back when you're ready.")
