# q25712s - Kaiser 4th job advancement

if chr.getJob() == 6111:
    if(sm.giveAndEquip(1352503)):
        sm.jobAdvance(6112)
        sm.completeQuest(25712)
    else:
        sm.sendSayOkay("Please clear a slot within your equip inventory.")
else:
    sm.sendSayOkay("You're currently not a third job Kaiser.")
sm.dispose()
