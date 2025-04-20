# Perion Warning Post (1022107) | Dusty Wind Hill
if sm.hasQuest(22530):
    if not sm.canHold(1952000):
        sm.sendSayOkay("You need inventory space.")
    else:
        sm.completeQuestNoRewards(22530)
        sm.giveItem(1952000)
        sm.sendSayOkay("You examine the sign. Finished the guard's request.")
else:
    sm.sendSayOkay("It's a sign.")
