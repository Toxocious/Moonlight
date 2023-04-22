# End of The Path of Bravery
KIMU = 1102004
sm.setSpeakerID(KIMU)
sm.removeEscapeButton()

sm.sendNext("Did you meet up with Kiku? He seems tough, but he's a total softy.")
sm.sendSay("The orientation's almost over. You wanna go ahead and get started on your training?")

sm.completeQuestNoRewards(parentID)
sm.warp(130030101) # Forest of Beginnings