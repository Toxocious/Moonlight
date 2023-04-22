# Sunburst  |  Part of the Golden Temple Quest Line

GATE_OF_TRIALS = 9000098

sm.setSpeakerID(GATE_OF_TRIALS)
sm.sendNext("#b(A voice resonates in your head again.) \r\n"
            "'The Sacrifice will protect thee, but it shall be destroyed the moment thou set foot in front of the golden altar! "
            "If thou dost not enter, the sacrifice will not be taken.' \r\n"
            "(Massive doors open the way to the Entrance to Ravana's Altar.)")

sm.warp(252030000, 0) # Entrance to Ravana's Altar
sm.completeQuestNoRewards(parentID)
