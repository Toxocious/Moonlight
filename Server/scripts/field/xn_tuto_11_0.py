# Video Field (931050990)  |  A Black Field used for Effects/Videos

job = sm.getChr().getJob()
if job == 2004: # Luminous Beginner Job ID
    sm.lockInGameUI(True)
    sm.showScene("Effect.wz/Direction8.img", "lightningTutorial", "Scene0")
    sm.invokeAfterDelay(5000, "warpInstanceIn", 927020000, 0) # Warp into Instance
    sm.invokeAfterDelay(4500, "showFadeTransition", 0, 500, 1500)
elif job == 2001: # Evan Beginner Job ID
    sm.lockInGameUI(True)
    sm.invokeAfterDelay(3000, "lockInGameUI", False)

    sm.showScene("Effect.wz/Direction4.img", "crash", "Scene0")
elif job == 3002:  # Xenon Beginner Job ID
    sm.lockInGameUI(True)
    # TODO: play youtube video of xenon with beryl
    sm.warpInstanceIn(931060060)
