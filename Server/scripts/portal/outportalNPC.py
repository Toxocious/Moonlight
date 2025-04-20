THE_MYSTERIOUS_GIRL = 30002

if sm.hasQuest(THE_MYSTERIOUS_GIRL):
    sm.setPlayerAsSpeaker()
    sm.completeQuest(THE_MYSTERIOUS_GIRL)
    sm.dispose()
    sm.sendNext("I should tell that girl about the exit first.")
else:
    sm.warp(105010200, 0) # Secret Swamp