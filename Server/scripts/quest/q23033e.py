# 23033 - BaM 3rd job advancement

sm.setSpeakerID(2151001)
if sm.sendAskYesNo("Are you ready to advance to the next level?"):
    sm.jobAdvance(3211)
    sm.completeQuest(parentID)
    sm.sendSayOkay("Good job on defeating the conductor device. You have advanced a job level, and I have given you some SP.")
else:
    sm.sendSayOkay("Come back when you're ready.")
