# Secret Swamp (Part of Root Abyss Quest Line)

if sm.hasQuest(30000):
    #sm.lockInGameUI(True)
    sm.sendDelay(3000)

elif sm.hasQuest(30003):
    sm.setPlayerAsSpeaker()
    sm.sendNext("Hmm.. It seems to work fine. Let's go back to tell her about the exit.")
    sm.dispose()

else:
    sm.dispose()


    sm.setPlayerAsSpeaker()
    sm.sendNext("This fog is too thick! I gotta keep my senses sharp. Who knows what's lurking out there...")

    sm.lockInGameUI(False)
