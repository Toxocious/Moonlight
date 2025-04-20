# 140000000
if not "ck=1" in sm.getQRValue(21019) and sm.hasQuestCompleted(21101):
    sm.lockInGameUI(False, False)
    sm.addQRValue(21019, "ck=1")