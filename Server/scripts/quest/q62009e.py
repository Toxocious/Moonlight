# [Shaolin Temple] Cleaning House

JANITOR_MONK = 9310048
SUTRA_3_4F = 701220200

sm.removeEscapeButton()
sm.setSpeakerID(JANITOR_MONK)
sm.setBoxChat()
sm.sendNext("You've thinned them out a bit. Hurry up to #r#m"+ str(SUTRA_3_4F) +"##k! #rUse the portal on the upper right to get there.")
sm.completeQuest(parentID)
sm.giveExp(2020453)