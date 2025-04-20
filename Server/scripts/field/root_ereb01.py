# Empress Road | Ereve (913080001) | Fake map used for Root Abyss Cutscene

ALICIA = 1064024
if sm.hasQuest(30013):
    sm.lockInGameUI(True)
    sm.showFieldEffect("Map/Effect.img/rootabyss/goEreb")
    sm.showFieldEffect("Effect/Direction11.img/effect/meet/frame0/0")
    sm.invokeAfterDelay(3000, "showFadeTransition", 2500, 0, 500)

    sm.spawnNpc(ALICIA, 275, 88) # Spawn Alicia

    sm.setSpeakerID(ALICIA)
    sm.invokeAfterDelay(5500, "sendNext", "It's so nice and pleasant here!")
else:
    sm.dispose()


    sm.setPlayerAsSpeaker()
    sm.sendNext("I hope you regain your powers soon.")

    sm.setSpeakerID(ALICIA)
    sm.sendNext("So do I, \r\n"
                "I believe Ereve will be a fine place to stay for the time being. "
                "Thank you for everything you've done #h0#.")

    sm.setPlayerAsSpeaker()
    sm.sendNext("Alright, I'll leave you be.")

    sm.lockInGameUI(False)
    sm.completeQuest(30013) # [Root Abyss] World Tree Rescue
    sm.warp(130000000, 0) # Ereve
    sm.dispose()
