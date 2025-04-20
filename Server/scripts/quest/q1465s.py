#   [Job Adv] (5th job)   5th Job : Record of power

sm.setSpeakerID(2140001)
if sm.sendAskYesNo("But these stones are not yours yet. To make them truly yours, you must mark them with you power. \r\n #b(Activate the Arcane Stone of the Goddess and hunt monsters near your level. The Arcane Stone will record you EXP gain and once activated they will give you the EXP saved in them one more time.)"):
    sm.startQuest(parentID)
    sm.sendSayOkay("Don't forget to activate all the three Arcane Stones.")
    sm.dispose()

    #todo add quest completion of 1474 1475 and 1476 to arcane stones