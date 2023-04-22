if not sm.hasMobsInField():
    sm.stopEvents()
    sm.warpInstanceIn(701220600)
    sm.createQuestWithQRValue(62038, "clear")
else:
    sm.chat("Please eliminate all monsters before moving to the next stage.")