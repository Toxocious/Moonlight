# q25711s - Kaiser 3rd job advancement

if chr.getJob() == 6110:
    if(sm.giveAndEquip(1352502)):
        sm.jobAdvance(6111)
        sm.completeQuest(25711)
    else:
        sm.sendSayOkay("Please clear a slot within your equip inventory.")
else:
    sm.sendSayOkay("You're currently not a second job Kaiser.")
sm.dispose()
