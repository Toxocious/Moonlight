if sm.hasQuest(1846):
    sm.lockInGameUI(True)
    sm.showScene("Effect.wz/Direction5.img", "evolvingDereciton", "Scene1")
    sm.sendDelay(11000)
    sm.lockInGameUI(True)
    sm.warp(957020006)
else:
    sm.lockInGameUI(True)
    sm.showScene("Effect.wz/Direction5.img", "evolvingDereciton", "Scene0")
    sm.invokeAfterDelay(3500, "showFadeTransition", 2500, 0, 500)
    sm.invokeAfterDelay(4000, "lockInGameUI", False)
    #TODO ugly workaround
    sm.deleteQuest(1801)
    sm.createQuestWithQRValue(1801, "end")