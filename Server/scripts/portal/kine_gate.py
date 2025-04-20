if sm.hasQuest(22780) or sm.hasQuestCompleted(22780):
    KINESIS = 1531000
    sm.setNpcOverrideBoxChat(KINESIS)
    if sm.getFieldID() == 101020400:
        if sm.sendAskYesNo("Return to Seoul?"):
            sm.warp(331001000, 4)
    elif sm.getFieldID() == 331001000:
        if sm.sendAskYesNo("Return to Maple World?"):
            sm.warp(101020400, 5)