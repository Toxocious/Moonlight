# Root Abyss | Colossal Root  -  Quest Map where there is only the NPC, no portals to bosses.

AN_URGENT_SUMMONS = 30000 # Quest Id

if sm.hasQuest(30000):
    sm.lockInGameUI(True)
    sm.invokeAfterDelay(23500, "lockInGameUI", False)
    sm.completeQuest(30000) # Complete Quest   [Root Abyss] An Urgent Summons


    sm.showScene("Effect.wz/Direction11.img", "meet", "Scene0") # Falling
    sm.invokeAfterDelay(3000, "showScene", "Effect.wz/Direction11.img", "meet", "Scene1") # "Wake Up!!!" text
    sm.invokeAfterDelay(9500, "showScene", "Effect.wz/Direction11.img", "meet", "Scene2") # "Mysterious Girl"

    # Custom Fade Out
    sm.invokeAfterDelay(22500, "showFadeTransition", 1500, 0, 1500)


elif sm.hasQuest(30003):
    sm.completeQuest(30003)
sm.dispose()
