GUARD1 = 9300498
GUARD2 = 9300507

if sm.hasQuest(25000):
    #sm.removeEscapeButton()
    #sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("This portal leads straight into Ereve. The place is going to be positively crawling with knights. Sounds like just my kind of place.")
    #sm.startQuestNoCheck(25003)
    sm.levelUntil(10)
    sm.jobAdvance(2400)
    sm.completeQuest(25000)
    sm.warpInstanceIn(100000000, 1)
    sm.dispose()