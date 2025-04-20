# [Riena Strait] A Warrior's Pride

PUTAN = 1510000

sm.setSpeakerID(PUTAN)
response = sm.sendAskYesNo("I think we need to go to the house of witch Barbara. All of us need to. Hmm hmm.. I think it would be nice if you would come with us.\r\n\r\n"
            "#b#e(Accepting will automatically move you.)#n#k")

if response:
    sm.warpInstanceIn(141040003, 0)
    sm.createQuestWithQRValue(parentID, "1")