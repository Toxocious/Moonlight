LADY_SYL = 1056000

sm.setSpeakerID(LADY_SYL)
if sm.sendAskAccept("Perhaps Ryden was right about you, #h0#. You look a little more powerful, and you've been carrying out my missions adequately. Well! Return to me in #bThe Secret Garden#k, and I'll teach you about the true power of Dual Blades.\r\n\r\n#b#e(You will be moved to Lady Syl's Room in the Secret Garden when you accept.)#n#k"):
    sm.startQuest(parentID)
    sm.warp(103050101, 3)