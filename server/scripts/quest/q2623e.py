LADY_SYL = 1056000

sm.setSpeakerID(LADY_SYL)
if sm.sendAskYesNo("So, the Mirror of Insight has chosen you. Very well. I will promote you to Blade Recruit when you are ready."):
    sm.completeQuest(parentID)
    sm.consumeItem(4032616)
    sm.giveItem(1342000)
    sm.jobAdvance(430)
    sm.sendNext("You are now a #b#eBlade Recruit#n#k. Take pride in that fact.")
else:
    sm.sendSayOkay("Why hesitate? What are you afraid of?")
    sm.dispose()