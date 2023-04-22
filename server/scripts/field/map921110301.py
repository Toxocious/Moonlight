import random

if sm.hasQuestCompleted(38074) and not sm.hasQuestCompleted(38075):
    sm.setPlayerAsSpeaker()
    sm.removeEscapeButton()
    sm.startQuest(38075)

    if not sm.hasReactors():
        position = random.randrange(8)
        sm.spawnReactor(2119007, 936 + (position + 1)*175, 43)
        for x in range(8):
            if x != position:
                sm.spawnReactor(2119008, 936 + (x + 1)*175, 43)

    sm.sendNext("I think I'm at the right place, but why are there so many boxes? I suppose I need to break them one at a time.")
    sm.chatScript("Use normal attacks to break the wooden boxes.")
