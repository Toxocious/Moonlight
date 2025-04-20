# Dark Lord | Kerning City Hideout
if sm.getChr().getLevel() >= 10 and sm.getChr().getJob() == 0:
    if sm.sendAskYesNo("Would you like to become a Thief?"):
        sm.giveItem(1472000)
        sm.giveItem(1332007)
        sm.giveItem(2070000, 3000)
        sm.setJob(400)
        sm.addSP(3)
        sm.completeQuest(1400)


