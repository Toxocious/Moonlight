# # Hidden Street : Logo - Black Field used for Xenon Logo

job = sm.getChr().getJob()
if job == 3002:  # Xenon Beginner Job ID
    sm.lockInGameUI(True)
    sm.showScene("Effect.wz/Direction12.img", "XenonTutorial", "SceneLogo")
    sm.invokeAfterDelay(9000, "warpInstanceIn", 931050900, 0)
    sm.invokeAfterDelay(7000, "showFadeTransition", 0, 500, 1500)
    sm.invokeAfterDelay(9000, "lockInGameUI", False)